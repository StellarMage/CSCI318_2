package com.remotegroup.sales.exceptions;

import com.remotegroup.sales.domain.model.aggregates.SaleId;

public class SaleNotFoundException extends RuntimeException {
	public SaleNotFoundException(SaleId id){
		super("Could not find Sale "+id);
	}
}
