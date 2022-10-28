package com.remotegroup.sales.service;

import com.remotegroup.sales.sale.domain.*;
import com.remotegroup.sales.sale.persistence.*;
import com.remotegroup.sales.shareddomain.BusinessIntelligence;
import com.remotegroup.sales.shareddomain.Product;
import com.remotegroup.sales.instoresale.domain.*;
import com.remotegroup.sales.instoresale.persistence.*;
import com.remotegroup.sales.listener.KafkaListeners;
import com.remotegroup.sales.onlinesale.domain.*;
import com.remotegroup.sales.onlinesale.persistence.*;
import com.remotegroup.sales.backordersale.domain.*;
import com.remotegroup.sales.backordersale.persistence.*;
import com.remotegroup.sales.controller.Controller;
import com.remotegroup.sales.exceptions.*;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SaleServiceImpl implements SaleService{

	@Autowired private final SaleRepository saleRepository;
	@Autowired private final InStoreSaleRepository inStoreSaleRepository;
	@Autowired private final OnlineSaleRepository onlineSaleRepository;
	@Autowired private final BackOrderSaleRepository backOrderSaleRepository;
	private final RestTemplate restTemplate;
	@Autowired private final  Controller controller;
	@Autowired private final  KafkaListeners kafkaListeners;
	private static final Logger log = LoggerFactory.getLogger(SaleServiceImpl.class);
	private ObjectMapper mapper = new ObjectMapper();
	
	SaleServiceImpl(SaleRepository saleRepository, InStoreSaleRepository i, OnlineSaleRepository o, BackOrderSaleRepository b, RestTemplateBuilder restTemplateBuilder, Controller controller, KafkaListeners kafkaListeners){
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
	public Sale createSale(Sale s) {
		return saleRepository.save(s);
	}

	@Override
	public Sale updateSale(Sale s, Long id) {
		return saleRepository.findById(id)
		      	.map(Sale -> {
		            Sale.setItemId(s.getItemId());
		            Sale.setItemName(s.getItemName());
		            Sale.setQuantity(s.getQuantity());
		            Sale.setDataTime(s.getDataTime());
		        return saleRepository.save(Sale);
		      })
		      	.orElseGet(() -> {
		        	s.setId(id);
		        	return saleRepository.save(s);
		      });
		
	}

	@Override
	public Sale getSale(Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return saleRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new SaleNotFoundException(id);
		}
	}

	@Override
	public void deleteSale(Long id) {
		saleRepository.deleteById(id);
	}
	
	// Communicate with Inventory Service via REST
	@Override
	public boolean requestCheckInventory(Long itemId){
		String url = "http://localhost:8081/product/check/"+itemId;
		return this.restTemplate.getForObject(url, boolean.class);
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
	public void sendSale() throws JsonProcessingException {
		int i = 2;
		long id = Long.valueOf(i);
		log.info("Sale " + getSale(id));
      	String jsonString = mapper.writeValueAsString(getSale(id));
		log.info("JSON " + jsonString);
		controller.businessIntelligence(jsonString);
	}
}
