package com.remotegroup.sales.exceptions;

public class BackOrderSaleNotFoundException extends Exception {
    public BackOrderSaleNotFoundException(Long id){
		super("Could not find BackOrderSale "+id);
	}
}
