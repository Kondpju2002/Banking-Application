package com.cts.hcbanking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	
    private String name;
    private Long id;
 
    public ResourceNotFoundException(String name, Long id) {
        // formatting the string of the form :
        // eg : Patient not found with id : 1
        super(String.format("%s not found with id :%s",name,id));
        this.name = name;
        this.id= id;
    }
}
