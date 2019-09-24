package com.addcel.PuntoWeb.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception cuando algun dato de entrada al servicio no es correcto
 */
@Getter
@Setter
public class DataInputException extends Exception {
    private Integer code;

    public DataInputException(String message) {
        super(message);
    }

    public DataInputException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
