package com.addcel.PuntoWeb.bean;

import lombok.Data;

@Data
public class PuntoWebResponse {
    public PuntoWebResponse() {
        this.code = 1; //por default codigo de EXITO
        this.message = "";
    }

    public PuntoWebResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public PuntoWebResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Integer code;
    private String message;
    private Object data;
}
