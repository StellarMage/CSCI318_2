package com.remotegroup.inventory.exceptions;

public class PartNotFoundByProductException extends RuntimeException {
	public PartNotFoundByProductException(Long id){
		super("Could not find part with Product "+id);
	}
}