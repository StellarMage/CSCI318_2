package com.remotegroup.sales.service;

import java.util.List;

import com.remotegroup.inventory.Product;

public interface SaleService {
	
	//expose Sale endpoints
	public abstract List<Sale> getSales();
	public abstract Sale createSale(Sale s);
	public abstract Sale updateSale(Sale s, Long id);
	public abstract Sale getSale(Long id);
	public abstract void deleteSale(Long id);
	public abstract boolean requestCheckInventory(Long itemId);
	
	public abstract List<InStoreSale> getInStoreSales();
	public abstract InStoreSale createSale(InStoreSale s);
	public abstract InStoreSale updateSale(InStoreSale s, Long id);
	public abstract InStoreSale getInStoreSale(Long id);
	public abstract void deleteInStoreSale(Long id);
	public abstract List<InStoreSale> lookupSalesByStore(Long storeId);
	
	public abstract List<OnlineSale> getOnlineSales();
	public abstract OnlineSale createSale(OnlineSale s);
	public abstract OnlineSale updateSale(OnlineSale s, Long id);
	public abstract OnlineSale getOnlineSale(Long id);
	public abstract void deleteOnlineSale(Long id);
	
	public abstract List<BackOrderSale> getBackOrderSales();
	public abstract BackOrderSale createBackOrderSale(BackOrderSale s);
	public abstract void deleteBackOrderSale(Long id);
	public abstract BackOrderSale getBackOrderSale(Long id) throws BackOrderSaleNotFoundException;

	public abstract Product getProductInfo(Long id);
}
