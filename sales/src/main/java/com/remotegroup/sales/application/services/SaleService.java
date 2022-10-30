package com.remotegroup.sales.application.services;


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
import com.remotegroup.sales.domain.model.aggregates.BackOrderSale;
import com.remotegroup.sales.domain.model.aggregates.InStoreSale;
import com.remotegroup.sales.domain.model.aggregates.OnlineSale;
import com.remotegroup.sales.domain.model.aggregates.Sale;
import com.remotegroup.sales.domain.model.aggregates.SaleId;
import com.remotegroup.sales.domain.model.aggregates.StoreId;
import com.remotegroup.sales.domain.model.commands.CreateBackOrderSaleCommand;
import com.remotegroup.sales.domain.model.commands.CreateInStoreSaleCommand;
import com.remotegroup.sales.domain.model.commands.CreateOnlineSaleCommand;
import com.remotegroup.sales.domain.model.commands.CreateSaleCommand;
import com.remotegroup.sales.domain.model.commands.UpdateInStoreSaleCommand;
import com.remotegroup.sales.domain.model.commands.UpdateOnlineSaleCommand;
import com.remotegroup.sales.domain.model.commands.UpdateSaleCommand;
import com.remotegroup.sales.domain.model.services.ISaleService;
import com.remotegroup.sales.domain.model.valueobjects.ItemId;
import com.remotegroup.sales.exceptions.BackOrderSaleNotFoundException;
import com.remotegroup.sales.exceptions.SaleNotFoundException;
import com.remotegroup.sales.infrastructure.persistence.BackOrderSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.InStoreSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.OnlineSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.SaleRepository;
import com.remotegroup.sales.interfaces.kafka.KafkaController;
import com.remotegroup.sales.interfaces.kafka.KafkaListeners;
import com.remotegroup.sales.shareddomain.Product;

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
    public boolean requestCheckInventory(Long itemId){
        String url = "http://localhost:8081/product/check/"+itemId;
        return this.restTemplate.getForObject(url, boolean.class);
    }
	
	//INSTORE

	@Override
	public List<InStoreSale> getInStoreSales() {
		  return inStoreSaleRepository.findAll();
	}

	@Override
	public InStoreSale createSale(CreateInStoreSaleCommand s) {
		String saleIdStr = UUID.randomUUID().toString().toUpperCase();
		s.setSaleId(saleIdStr);
		
		return inStoreSaleRepository.save(new InStoreSale(s));	
	}

	@Override
	public InStoreSale updateSale(UpdateInStoreSaleCommand s) {
		//find the sale by saleId
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("saleId", match->match.exact());
		InStoreSale sExample = new InStoreSale();
		sExample.setSaleId(new SaleId(s.getSaleId()));
		Example<InStoreSale> example = Example.of(sExample, matcher);
		
		//store sale in object
		List<InStoreSale> returnSales =  inStoreSaleRepository.findAll(example);
		InStoreSale sale = returnSales.get(0);
		
		return sale.updateInStoreSale(s);
	}

	@Override
	public InStoreSale getInStoreSale(SaleId id) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("saleId", match->match.exact());
		InStoreSale sExample = new InStoreSale();
		sExample.setSaleId(id);
		Example<InStoreSale> example = Example.of(sExample, matcher);
		
		//store sale in object
		List<InStoreSale> returnSales =  inStoreSaleRepository.findAll(example);
		InStoreSale sale = returnSales.get(0);
		return sale;
	}

	@Override
	public void deleteInStoreSale(SaleId id) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("saleId", match->match.exact());
		InStoreSale sExample = new InStoreSale();
		sExample.setSaleId(id);
		Example<InStoreSale> example = Example.of(sExample, matcher);
		
		//store sale in object
		List<InStoreSale> returnSales =  inStoreSaleRepository.findAll(example);
		InStoreSale sale = returnSales.get(0);
		
		inStoreSaleRepository.delete(sale);
		
	}
	
	public List<InStoreSale> lookupSalesByStore(StoreId storeId) {
		List<InStoreSale> sales = inStoreSaleRepository.findAll();
		List<InStoreSale> matches = new ArrayList<InStoreSale>();
		for(int s = 0; s<sales.size();s++) {
			InStoreSale sale = sales.get(s);
			if(sale.getStoreId().equals(storeId)) {
				matches.add(sale);
			}
		}
		return matches;
	}

	//ONLINE
	
	@Override
	public List<OnlineSale> getOnlineSales() {
		  return onlineSaleRepository.findAll();
	}

	@Override
	public OnlineSale createSale(CreateOnlineSaleCommand s) {
		String saleIdStr = UUID.randomUUID().toString().toUpperCase();
		s.setSaleId(saleIdStr);
		
		return onlineSaleRepository.save(new OnlineSale(s));	
	}

	@Override
	public OnlineSale updateSale(UpdateOnlineSaleCommand s) {
		//find the sale by saleId
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("saleId", match->match.exact());
		OnlineSale sExample = new OnlineSale();
		sExample.setSaleId(new SaleId(s.getSaleId()));
		Example<OnlineSale> example = Example.of(sExample, matcher);
		
		//store sale in object
		List<OnlineSale> returnSales =  onlineSaleRepository.findAll(example);
		OnlineSale sale = returnSales.get(0);
		return sale.updateOnlineSale(s);

	}

	@Override
	public OnlineSale getOnlineSale(SaleId id) {
		//find the sale by saleId
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("saleId", match->match.exact());
		OnlineSale sExample = new OnlineSale();
		sExample.setSaleId(id);
		Example<OnlineSale> example = Example.of(sExample, matcher);
		
		//store sale in object
		List<OnlineSale> returnSales =  onlineSaleRepository.findAll(example);
		OnlineSale sale = returnSales.get(0);
		return sale;
	}

	@Override
	public void deleteOnlineSale(SaleId id) {
		//find the sale by saleId
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("saleId", match->match.exact());
		OnlineSale sExample = new OnlineSale();
		sExample.setSaleId(id);
		Example<OnlineSale> example = Example.of(sExample, matcher);
		
		//store sale in object
		List<OnlineSale> returnSales =  onlineSaleRepository.findAll(example);
		OnlineSale sale = returnSales.get(0);
		onlineSaleRepository.delete(sale);
		
	}
	
	
	//BACK ORDER

	@Override
	public List<BackOrderSale> getBackOrderSales() {
		  return backOrderSaleRepository.findAll();
	}

	@Override
	public BackOrderSale createBackOrderSale(CreateBackOrderSaleCommand s) throws JsonProcessingException {
		String saleIdStr = UUID.randomUUID().toString().toUpperCase();
		s.setSaleId(saleIdStr);
		
		BackOrderSale sale = new BackOrderSale(s);
		
		String jsonString = mapper.writeValueAsString(sale);
		controller.procure(jsonString);
		log.info("Procurement Request Sent");
		
		
		return backOrderSaleRepository.save(sale);
	}

	@Override
	public void deleteBackOrderSale(SaleId id) {
		//find the sale by saleId
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("saleId", match->match.exact());
		BackOrderSale sExample = new BackOrderSale();
		sExample.setSaleId(id);
		Example<BackOrderSale> example = Example.of(sExample, matcher);
		
		//store sale in object
		List<BackOrderSale> returnSales =  backOrderSaleRepository.findAll(example);
		BackOrderSale sale = returnSales.get(0);
		backOrderSaleRepository.delete(sale);
		
	}

	@Override
	public BackOrderSale getBackOrderSale(SaleId id) throws BackOrderSaleNotFoundException {
		//find the sale by saleId
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("saleId", match->match.exact());
		BackOrderSale sExample = new BackOrderSale();
		sExample.setSaleId(id);
		Example<BackOrderSale> example = Example.of(sExample, matcher);
		
		//store sale in object
		List<BackOrderSale> returnSales =  backOrderSaleRepository.findAll(example);
		BackOrderSale sale = returnSales.get(0);
		return sale;
	}
	
	@Override
	public Product getProductInfo(SaleId id) {
		try {
			Sale chosenSale = getSale(id);
			ItemId itemId = chosenSale.getItemId();

			String jsonString = mapper.writeValueAsString(itemId);
			controller.publish(jsonString);
			log.info("ItemId Sent to Inventory");
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