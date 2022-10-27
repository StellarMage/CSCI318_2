package com.remotegroup.sales.exceptions;

public class OnlineSaleNotFoundException extends RuntimeException {
	public OnlineSaleNotFoundException(Long id){
		super("Could not find Online Sale "+id);
	}
}
