package org.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFOundException extends RuntimeException {
    public ResourceNotFOundException(String resourceName,String fieldName,String message){
        super(String.format("%s not found with the given input data %s: %s",resourceName,fieldName,message));
    }
}
