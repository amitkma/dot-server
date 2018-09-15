package com.dot.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OtpException extends RuntimeException {
    public OtpException(String msg){
        super(msg);
    }
}
