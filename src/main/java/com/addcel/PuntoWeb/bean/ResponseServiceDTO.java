package com.addcel.PuntoWeb.bean;

import com.addcel.PuntoWeb.client.response.Autorizacion;
import lombok.Data;

@Data
public class ResponseServiceDTO {
    private Autorizacion autorizacion;
    private boolean exito;
    private String message;

    public ResponseServiceDTO() {
        this.exito = true;
    }
}
