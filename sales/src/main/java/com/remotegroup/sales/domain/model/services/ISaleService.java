package com.remotegroup.sales.domain.model.services;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.remotegroup.sales.domain.model.aggregates.BackOrderSale;
import com.remotegroup.sales.domain.model.aggregates.InStoreSale;
import com.remotegroup.sales.domain.model.aggregates.OnlineSale;
import com.remotegroup.sales.domain.model.aggregates.Sale;
import com.remotegroup.sales.domain.model.aggregates.SaleId;
<<<<<<< HEAD
import com.remotegroup.sales.domain.model.aggregates.StoreId;
import com.remotegroup.sales.domain.model.commands.CreateBackOrderSaleCommand;
import com.remotegroup.sales.domain.model.commands.CreateInStoreSaleCommand;
import com.remotegroup.sales.domain.model.commands.CreateOnlineSaleCommand;
import com.remotegroup.sales.domain.model.commands.CreateSaleCommand;
import com.remotegroup.sales.domain.model.commands.UpdateInStoreSaleCommand;
import com.remotegroup.sales.domain.model.commands.UpdateOnlineSaleCommand;
import com.remotegroup.sales.domain.model.commands.UpdateSaleCommand;
import com.remotegroup.sales.exceptions.BackOrderSaleNotFoundException;
import com.remotegroup.sales.shareddomain.Product;
=======
import com.remotegroup.sales.domain.model.commands.*;
import com.remotegroup.sales.domain.model.valueobjects.ItemId;
import com.remotegroup.sales.exceptions.*;
import com.remotegroup.sales.shareddomain.*;

import java.util.List;
>>>>>>> 7a650829a2a283a420d74b56f45cb54cbb24cb9a

public interface ISaleService {
	
	//expose Sale endpoints
	public abstract List<Sale> getSales();
	public abstract Sale createSale(CreateSaleCommand s) throws JsonProcessingException;
	public abstract Sale updateSale(UpdateSaleCommand s);
	public abstract Sale getSale(SaleId id);
	public abstract void deleteSale(SaleId id);
	public abstract boolean requestCheckInventory(Long itemId);
	
	public abstract List<InStoreSale> getInStoreSales();
	public abstract InStoreSale createSale(CreateInStoreSaleCommand s) throws JsonProcessingException;
	public abstract InStoreSale updateSale(UpdateInStoreSaleCommand s);
	public abstract InStoreSale getInStoreSale(SaleId id);
	public abstract void deleteInStoreSale(SaleId id);
	public abstract List<InStoreSale> lookupSalesByStore(StoreId storeId);
	
	public abstract List<OnlineSale> getOnlineSales();
	public abstract OnlineSale createSale(CreateOnlineSaleCommand s);
	public abstract OnlineSale updateSale(UpdateOnlineSaleCommand s);
	public abstract OnlineSale getOnlineSale(SaleId id);
	public abstract void deleteOnlineSale(SaleId id);
	
	public abstract List<BackOrderSale> getBackOrderSales();
	public abstract BackOrderSale createBackOrderSale(CreateBackOrderSaleCommand s) throws JsonProcessingException;
	public abstract void deleteBackOrderSale(SaleId id);
	public abstract BackOrderSale getBackOrderSale(SaleId id) throws BackOrderSaleNotFoundException;

	public abstract Product getProductInfo(SaleId id);

	public abstract void initSaleBI() throws JsonProcessingException;
	public abstract void sendSale(Sale s) throws JsonProcessingException;
	public abstract void sendUpdateSale(Sale s) throws JsonProcessingException;
}
