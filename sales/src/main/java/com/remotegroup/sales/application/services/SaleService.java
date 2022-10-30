package com.remotegroup.sales.application.services;

import com.remotegroup.sales.shareddomain.Product;
import com.remotegroup.sales.interfaces.kafka.KafkaController;
import com.remotegroup.sales.interfaces.kafka.KafkaListeners;
import com.remotegroup.sales.domain.model.aggregates.BackOrderSale;
import com.remotegroup.sales.domain.model.aggregates.InStoreSale;
import com.remotegroup.sales.domain.model.aggregates.OnlineSale;
import com.remotegroup.sales.domain.model.aggregates.Sale;
import com.remotegroup.sales.domain.model.aggregates.SaleId;
import com.remotegroup.sales.domain.model.commands.*;
import com.remotegroup.sales.domain.model.services.ISaleService;
import com.remotegroup.sales.exceptions.*;
import com.remotegroup.sales.infrastructure.persistence.BackOrderSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.InStoreSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.OnlineSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.SaleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SaleService implements ISaleService{

	@Autowired private final SaleRepository saleRepository;
	@Autowired private final InStoreSaleRepository inStoreSaleRepository;
	@Autowired private final OnlineSaleRepository onlineSaleRepository;
	@Autowired private final BackOrderSaleRepository backOrderSaleRepository;
	private final RestTemplate restTemplate;
	@Autowired private final  KafkaController controller;
	@Autowired private final  KafkaListeners kafkaListeners;
	private static final Logger log = LoggerFactory.getLogger(SaleService.class);
	private ObjectMapper mapper = new ObjectMapper();
	
	SaleService(SaleRepository saleRepository, InStoreSaleRepository i, OnlineSaleRepository o, BackOrderSaleRepository b, RestTemplateBuilder restTemplateBuilder, KafkaController controller, KafkaListeners kafkaListeners){
		this.saleRepository = saleRepository;
		this.inStoreSaleRepository = i;
		this.onlineSaleRepository = o;
		this.backOrderSaleRepository = b;
		this.restTemplate = restTemplateBuilder.build();
		this.controller = controller;
		this.kafkaListeners = kafkaListeners;
	}
	
	@Override
	public List<Sale> getSales() {
		  return saleRepository.findAll();
	}

	@Override
	public Sale createSale(CreateSaleCommand createSaleCommand) {
		String saleIdStr = UUID.randomUUID().toString().toUpperCase();
		createSaleCommand.setSaleId(saleIdStr);
		
		return saleRepository.save(new Sale(createSaleCommand));
	}

	@Override
	public Sale updateSale(UpdateSaleCommand updateSaleCommand) {
		
		//find the sale by saleId
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("saleId", match->match.exact());
		Sale sExample = new Sale();
		sExample.setSaleId(new SaleId(updateSaleCommand.getSaleId()));
		Example<Sale> example = Example.of(sExample, matcher);
		
		//store sale in object
		List<Sale> returnSales =  saleRepository.findAll(example);
		Sale sale = returnSales.get(0);
		
		//update sale
		sale.updateSale(updateSaleCommand);
		
		//save to repository
		return saleRepository.save(sale);
	}

	@Override
	public Sale getSale(SaleId id) {
		//find the sale by saleId
		ExampleMatcher matcher = ExampleMatcher.matching()
			.withMatcher("saleId", match->match.exact());
		Sale sExample = new Sale();
		sExample.setSaleId(id);
		Example<Sale> example = Example.of(sExample, matcher);

		//store sale in object
		List<Sale> returnSales =  saleRepository.findAll(example);
		Sale sale = returnSales.get(0);
		return sale;
	}

	@Override
	public void deleteSale(SaleId id) {
		//find the sale by saleId
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("saleId", match->match.exact());
		Sale sExample = new Sale();
		sExample.setSaleId(id);
		Example<Sale> example = Example.of(sExample, matcher);
		
		//store sale in object
		List<Sale> returnSales =  saleRepository.findAll(example);
		Sale sale = returnSales.get(0);
		
		saleRepository.delete(sale);
	}

	@Override
	public List<InStoreSale> getInStoreSales() {
		  return inStoreSaleRepository.findAll();
	}

	@Override
	public InStoreSale createSale(InStoreSale s) {
		return inStoreSaleRepository.save(s);	
	}

	@Override
	public InStoreSale updateSale(InStoreSale s, Long id) {
		return inStoreSaleRepository.findById(id)
		      	.map(InStoreSale -> {
		      		InStoreSale.setItemId(s.getItemId());
		      		InStoreSale.setItemName(s.getItemName());
		      		InStoreSale.setQuantity(s.getQuantity());
		      		InStoreSale.setDataTime(s.getDataTime());
					InStoreSale.setStoreId(s.getStoreId());
		            InStoreSale.setReceipt(s.getReceiptNo());
		        return inStoreSaleRepository.save(InStoreSale);
		      })
		      	.orElseGet(() -> {
		        	s.setId(id);
		        	return inStoreSaleRepository.save(s);
		      });
	}

	@Override
	public InStoreSale getInStoreSale(Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return inStoreSaleRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new InStoreSaleNotFoundException(id);
		}
	}

	@Override
	public void deleteInStoreSale(Long id) {
		inStoreSaleRepository.deleteById(id);
		
	}
	
	public List<InStoreSale> lookupSalesByStore(Long storeId) {
		List<InStoreSale> sales = inStoreSaleRepository.findAll();
		List<InStoreSale> matches = new ArrayList<InStoreSale>();
		for(int s = 0; s<sales.size();s++) {
			InStoreSale sale = sales.get(s);
			if(sale.getStoreId() == storeId) {
				matches.add(sale);
			}
		}
		return matches;
	}

	@Override
	public List<OnlineSale> getOnlineSales() {
		  return onlineSaleRepository.findAll();
	}

	@Override
	public OnlineSale createSale(OnlineSale s) {
		return onlineSaleRepository.save(s);
	}

	@Override
	public OnlineSale updateSale(OnlineSale s, Long id) {
		return onlineSaleRepository.findById(id)
		      	.map(OnlineSale -> {
					OnlineSale.setCustomerName(s.getCustomerName());
		            OnlineSale.setAddress(s.getAddress());
		        return onlineSaleRepository.save(OnlineSale);
		      })
		      	.orElseGet(() -> {
		        	s.setId(id);
		        	return onlineSaleRepository.save(s);
		      });
	}

	@Override
	public OnlineSale getOnlineSale(Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return onlineSaleRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new OnlineSaleNotFoundException(id);
		}
	}

	@Override
	public void deleteOnlineSale(Long id) {
		onlineSaleRepository.deleteById(id);
		
	}

	@Override
	public List<BackOrderSale> getBackOrderSales() {
		  return backOrderSaleRepository.findAll();
	}

	@Override
	public BackOrderSale createBackOrderSale(BackOrderSale s) throws JsonProcessingException {
		String jsonString = mapper.writeValueAsString(s);
		controller.procure(jsonString);
		log.info("Procurement Request Sent");
		return backOrderSaleRepository.save(s);
	}

	@Override
	public void deleteBackOrderSale(Long id) {
		backOrderSaleRepository.deleteById(id);
		
	}

	@Override
	public BackOrderSale getBackOrderSale(Long id) throws BackOrderSaleNotFoundException {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return backOrderSaleRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new BackOrderSaleNotFoundException(id);
		}
	}
	
	@Override
	public Product getProductInfo(Long id) {
		try {
			Sale chosenSale = getSale(id);
			Long itemId = chosenSale.getItemId();
			String payload = Long.toString(itemId);
			controller.publish(payload);
			log.info("Sale ID Sent to Inventory");
			return kafkaListeners.getListener();
		}catch(Exception e) {
			throw new SaleNotFoundException(id);
		}
		
	}

	@Override
	public void initSaleBI() throws JsonProcessingException {
		int i = 2;
		long id = Long.valueOf(i);
		log.info("Sale " + getSale(id));
      	String jsonString = mapper.writeValueAsString(getSale(id));
		log.info("JSON " + jsonString);
		controller.bIInit(jsonString);
	}

	@Override
	public void sendSale(Sale s) throws JsonProcessingException {
      	String jsonString = mapper.writeValueAsString(s);
		log.info("JSON " + jsonString);
		controller.bISendSale(jsonString);
	}

	@Override
	public void sendUpdateSale(Sale s) throws JsonProcessingException {
      	String jsonString = mapper.writeValueAsString(s);
		log.info("JSON " + jsonString);
		controller.bISendUpdateSale(jsonString);
	}
}
