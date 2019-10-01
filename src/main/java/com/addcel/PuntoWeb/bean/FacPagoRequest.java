package com.addcel.PuntoWeb.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class FacPagoRequest implements Serializable {
    private String marcaTarjeta;
    private String monto;
    private String numeroTarjeta;
    private String fechaVigencia;
    private String ccv;
    private Integer idTarjetaUsuario;
    private String diferido;
    private String cuotas;
}
