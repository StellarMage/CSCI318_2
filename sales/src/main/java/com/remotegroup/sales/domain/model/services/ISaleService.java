package com.remotegroup.sales.domain.model.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.remotegroup.sales.domain.model.aggregates.BackOrderSale;
import com.remotegroup.sales.domain.model.aggregates.InStoreSale;
import com.remotegroup.sales.domain.model.aggregates.OnlineSale;
import com.remotegroup.sales.domain.model.aggregates.Sale;
import com.remotegroup.sales.domain.model.aggregates.SaleId;
import com.remotegroup.sales.domain.model.commands.*;
import com.remotegroup.sales.exceptions.*;
import com.remotegroup.sales.shareddomain.*;

import java.util.List;

public interface ISaleService {
	
	//expose Sale endpoints
	public abstract List<Sale> getSales();
	public abstract Sale createSale(CreateSaleCommand s) throws JsonProcessingException;
	public abstract Sale updateSale(UpdateSaleCommand s);
	public abstract Sale getSale(SaleId id);
	public abstract void deleteSale(SaleId id);
	public abstract boolean requestCheckInventory(String itemId);
	
	public abstract List<InStoreSale> getInStoreSales();
	public abstract InStoreSale createSale(InStoreSale s) throws JsonProcessingException;
	public abstract InStoreSale updateSale(InStoreSale s, Long id);
	public abstract InStoreSale getInStoreSale(Long id);
	public abstract void deleteInStoreSale(Long id);
	public abstract List<InStoreSale> lookupSalesByStore(Long storeId);
	
	public abstract List<OnlineSale> getOnlineSales();
	public abstract OnlineSale createSale(CreateOnlineSaleCommand s);
	public abstract OnlineSale updateSale(UpdateOnlineSaleCommand s);
	public abstract OnlineSale getOnlineSale(Long id);
	public abstract void deleteOnlineSale(Long id);
	
	public abstract List<BackOrderSale> getBackOrderSales();
	public abstract BackOrderSale createBackOrderSale(BackOrderSale s) throws JsonProcessingException;
	public abstract void deleteBackOrderSale(Long id);
	public abstract BackOrderSale getBackOrderSale(Long id) throws BackOrderSaleNotFoundException;

	public abstract Product getProductInfo(Long id);

	public abstract void initSaleBI() throws JsonProcessingException;
	public abstract void sendSale(Sale s) throws JsonProcessingException;
	public abstract void sendUpdateSale(Sale s) throws JsonProcessingException;
}
