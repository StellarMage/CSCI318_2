package com.remotegroup.sales;

public class StoreNotFoundException extends RuntimeException {
	StoreNotFoundException(Long id){
		super("Could not find store "+id);
	}
}
