package com.remotegroup.sales;

public class SaleNotFoundException extends RuntimeException {
	SaleNotFoundException(Long id){
		super("Could not find sale "+id);
	}
}
