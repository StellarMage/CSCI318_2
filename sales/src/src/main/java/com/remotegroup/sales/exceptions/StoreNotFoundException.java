package com.remotegroup.sales.exceptions;

public class StoreNotFoundException extends RuntimeException {
	StoreNotFoundException(Long id){
		super("Could not find store "+id);
	}
}
