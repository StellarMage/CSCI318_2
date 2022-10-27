package com.remotegroup.sales.exceptions;

public class InStoreSaleNotFoundException extends RuntimeException {
	InStoreSaleNotFoundException(Long id){
		super("Could not find InStoreSale "+id);
	}
}