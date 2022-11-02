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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.remotegroup.sales.domain.model.aggregates.BackOrderSale;
import com.remotegroup.sales.domain.model.aggregates.InStoreSale;
import com.remotegroup.sales.domain.model.aggregates.OnlineSale;
import com.remotegroup.sales.domain.model.aggregates.Sale;
import com.remotegroup.sales.domain.model.aggregates.SaleId;
import com.remotegroup.sales.domain.model.aggregates.Store;
import com.remotegroup.sales.domain.model.aggregates.StoreId;
import com.remotegroup.sales.domain.model.commands.CreateBackOrderSaleCommand;
import com.remotegroup.sales.domain.model.commands.CreateInStoreSaleCommand;
import com.remotegroup.sales.domain.model.commands.CreateOnlineSaleCommand;
import com.remotegroup.sales.domain.model.commands.CreateSaleCommand;
import com.remotegroup.sales.domain.model.commands.CreateStoreCommand;
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
import com.remotegroup.sales.infrastructure.persistence.StoreRepository;
import com.remotegroup.sales.interfaces.kafka.KafkaController;
import com.remotegroup.sales.interfaces.kafka.KafkaListeners;
import com.remotegroup.sales.shareddomain.events.SaleEvent;
import com.remotegroup.shareddomain.model.aggregates.Product;

@Service
public class SaleService implements ISaleService{

	@Autowired private final SaleRepository saleRepository;
	@Autowired private final InStoreSaleRepository inStoreSaleRepository;
	@Autowired private final OnlineSaleRepository onlineSaleRepository;
	@Autowired private final BackOrderSaleRepository backOrderSaleRepository;
	@Autowired private final StoreRepository storeRepository;
	private final RestTemplate restTemplate;
	@Autowired private final  KafkaController controller;
	@Autowired private final  KafkaListeners kafkaListeners;
	private static final Logger log = LoggerFactory.getLogger(SaleService.class);
	private ObjectMapper mapper = new ObjectMapper();
	
	SaleService(SaleRepository saleRepository, InStoreSaleRepository i, StoreRepository st, OnlineSaleRepository o, BackOrderSaleRepository b, RestTemplateBuilder restTemplateBuilder, KafkaController controller, KafkaListeners kafkaListeners){
		this.saleRepository = saleRepository;
		this.inStoreSaleRepository = i;
		this.storeRepository = st;
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
		
		Sale createdSale = saleRepository.save(new Sale(createSaleCommand));

		return createdSale;
	}

	@Override
	public Sale updateSale(UpdateSaleCommand updateSaleCommand) {
		
		Sale sale = getSale(new SaleId(updateSaleCommand.getSaleId()));
		//update sale
		sale.updateSale(updateSaleCommand);
		
		//save to repository
		return saleRepository.save(sale);
	}

	@Override
	public Sale getSale(SaleId id) {
		List<Sale> sl = getSales();
		return sl.stream().filter(s-> {return s.getSaleId().equals(id);}).findAny().orElse(null);
	}

	@Override
	public void deleteSale(SaleId id) {

		Sale sale = getSale(id);
		
		saleRepository.delete(sale);
	}
	
	@Override
    public boolean requestCheckInventory(String itemId){
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
		InStoreSale sale = getInStoreSale(new SaleId(s.getSaleId()));
		
		return sale.updateInStoreSale(s);
	}

	@Override
	public InStoreSale getInStoreSale(SaleId id) {
		List<InStoreSale> sl = getInStoreSales();
		return sl.stream().filter(s-> {return s.getSaleId().equals(id);}).findAny().orElse(null);
	
	}

	@Override
	public void deleteInStoreSale(SaleId id) {

		InStoreSale sale = getInStoreSale(id);
		
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
	
	//STORE
	
	@Override
	public Store createStore(CreateStoreCommand c) {
		String storeIdStr = UUID.randomUUID().toString().toUpperCase();
		c.setStoreId(storeIdStr);
		
		return storeRepository.save(new Store(c));
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
		OnlineSale sale = getOnlineSale(new SaleId(s.getSaleId()));
		return sale.updateOnlineSale(s);

	}

	@Override
	public OnlineSale getOnlineSale(SaleId id) {
		List<OnlineSale> sl = getOnlineSales();
		return sl.stream().filter(s-> {return s.getSaleId().equals(id);}).findAny().orElse(null);
	
	}

	@Override
	public void deleteOnlineSale(SaleId id) {
		OnlineSale sale = getOnlineSale(id);
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
		BackOrderSale sale = getBackOrderSale(id);
		backOrderSaleRepository.delete(sale);
		
	}

	@Override
	public BackOrderSale getBackOrderSale(SaleId id) {
		List<BackOrderSale> sl = getBackOrderSales();
		return sl.stream().filter(s-> {return s.getSaleId().equals(id);}).findAny().orElse(null);
	
	}

	@Override
	public List<SaleId> getSaleIds(){
		List<SaleId> ids = new ArrayList<SaleId>();
		List<Sale> sales = getSales();
		for(int i=0;i<sales.size();i++) {
			ids.add(sales.get(i).getSaleId());
		}
		return ids;
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
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		String url = "http://localhost:8080/sales/ids";
		JsonNode saleIds = restTemplate.getForObject(url, JsonNode.class);
		log.info("Preloaded saleIds:  " + saleIds);
		  
		List<SaleId> ids = mapper.convertValue(saleIds, new TypeReference<List<SaleId>>() {});
		log.info("Sale " + getSale(ids.get(0)));
		log.info("Sale " + getSale(ids.get(1)));
		String jsonString = mapper.writeValueAsString(getSale(ids.get(0)));
		log.info("JSON " + jsonString);
		controller.bIInit(jsonString);
		String jsonString2 = mapper.writeValueAsString(getSale(ids.get(1)));
		log.info("JSON " + jsonString2);
		controller.bIInit(jsonString2);
	}

	@Override
	public void sendSale(SaleEvent s) throws JsonProcessingException {
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
	
	//
}
