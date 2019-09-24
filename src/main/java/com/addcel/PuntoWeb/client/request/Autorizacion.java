package com.addcel.PuntoWeb.client.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Autorizacion {
    @JacksonXmlProperty(localName = "Marca")
    private String marca;
    @JacksonXmlProperty(localName = "Comercio")
    private String comercio;
    @JacksonXmlProperty(localName = "Referencia")
    private String referencia;
    @JacksonXmlProperty(localName = "Monto")
    private String monto;
    @JacksonXmlProperty(localName = "Moneda")
    private String moneda;
    @JacksonXmlProperty(localName = "Tarjeta")
    private String tarjeta;
    @JacksonXmlProperty(localName = "CVC")
    private String cvc;
    @JacksonXmlProperty(localName = "FechaExp")
    private String fechaExp;
    @JacksonXmlProperty(localName = "Fecha")
    private String fecha;
    @JacksonXmlProperty(localName = "Hora")
    private String hora;
    @JacksonXmlProperty(localName = "Diferido")
    private String diferido;
    @JacksonXmlProperty(localName = "Cuotas")
    private String cuotas;
    @JacksonXmlProperty(localName = "TipoProceso")
    private String tipoProceso;
    @JacksonXmlProperty(localName = "Autogenerador")
    private String autogenerador;
    @JacksonXmlProperty(localName = "ComercioFacilitador")
    private String comercioFacilitador;
    @JacksonXmlProperty(localName = "DataEnc")
    private String dataEnc;
    @JacksonXmlProperty(localName = "Firma")
    private String firma;
}
