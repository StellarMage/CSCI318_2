package com.remotegroup.procurement.exceptions;

public class SupplierNotFoundException extends RuntimeException {
	public SupplierNotFoundException(Long id){
		super("Could not find supplier "+id);
	}
}
