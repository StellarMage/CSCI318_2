package com.remotegroup.sales.exceptions;

public class StoreNotFoundException extends RuntimeException {
	public StoreNotFoundException(Long id){
		super("Could not find Store "+id);
	}
}
