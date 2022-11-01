package com.remotegroup.shareddomain.events;

public class SaleEvent {
	SaleEventData data;
	
	public SaleEvent() {}
	
	public SaleEvent(SaleEventData sale) {
		this.data = new SaleEventData(sale.getItemName().toString(), sale.getQuantity(), sale.getProductPrice());
	}
	
	public SaleEventData getData() {return this.data;}
	
}
