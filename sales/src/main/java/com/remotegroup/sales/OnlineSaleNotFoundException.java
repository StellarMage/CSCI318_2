package com.remotegroup.sales;

public class OnlineSaleNotFoundException extends RuntimeException {
	OnlineSaleNotFoundException(Long id){
		super("Could not find online sale "+id);
	}
}
