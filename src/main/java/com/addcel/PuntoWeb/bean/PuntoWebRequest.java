package com.addcel.PuntoWeb.bean;

import lombok.Data;

import java.math.BigInteger;

@Data
public class PuntoWebRequest {
    private BigInteger idUsuario;
    private String monto;
    private String moneda;
    private String diferido;
    private String cuotas;
    private String tipoProceso;
    private String autogenerador;
    private String dataEnc;
    private String firma;
    private Integer idTarjetaUsuario;
    private boolean autorizacionManual;
    private String numeroTarjeta;
    private String fechaVigencia;
    private String ccv;
    private String concepto;
    private String comision;
    private BigInteger idEstablecimiento;
}
