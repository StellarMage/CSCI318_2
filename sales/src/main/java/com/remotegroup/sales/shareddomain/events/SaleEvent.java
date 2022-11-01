package com.remotegroup.sales.shareddomain.events;

import com.remotegroup.sales.domain.model.aggregates.Sale;

public class SaleEvent {
	SaleEventData data;
	
	public SaleEvent() {}
	
	public SaleEvent(Sale sale) {
		this.data = new SaleEventData(sale.getItemName().toString(), Integer.parseInt(sale.getQuantity().getValue()), Double.parseDouble(sale.getProductPrice().getValue()));
	}
	
	public SaleEventData getData() {return this.data;}
	
}
