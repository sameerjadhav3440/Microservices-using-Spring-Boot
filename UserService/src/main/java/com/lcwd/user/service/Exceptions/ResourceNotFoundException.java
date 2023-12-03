package com.lcwd.user.service.Exceptions;

public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(){
        super("Resource Not Found Exception");
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }



}
