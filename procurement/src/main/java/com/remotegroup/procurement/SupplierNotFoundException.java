package com.remotegroup.procurement;

public class SupplierNotFoundException extends RuntimeException {
	SupplierNotFoundException(Long id){
		super("Could not find supplier "+id);
	}
}
