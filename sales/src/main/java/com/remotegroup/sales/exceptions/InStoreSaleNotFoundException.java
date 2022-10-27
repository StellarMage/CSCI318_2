package com.remotegroup.sales.exceptions;

public class InStoreSaleNotFoundException extends RuntimeException {
	public InStoreSaleNotFoundException(Long id){
		super("Could not find In Store Sale "+id);
	}
}