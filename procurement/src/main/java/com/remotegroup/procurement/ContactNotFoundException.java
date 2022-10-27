package com.remotegroup.procurement;

public class ContactNotFoundException extends RuntimeException{
	ContactNotFoundException(Long id){
		super("could not find contact "+id);
	}
}
