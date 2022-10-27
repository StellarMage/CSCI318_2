package com.remotegroup.procurement.exceptions;

public class ContactNotFoundException extends RuntimeException{
	public ContactNotFoundException(Long id){
		super("could not find contact "+id);
	}
}
