package com.remotegroup.sales.request;

//Emulating Java 14 record
public class Request {
    
    final String message;

    public Request(String message){
        this.message = message;
    }

    String message() {return message;}
}
