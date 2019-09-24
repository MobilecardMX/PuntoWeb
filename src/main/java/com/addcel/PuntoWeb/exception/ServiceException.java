package com.addcel.PuntoWeb.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException extends Exception{
    private Integer code;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
