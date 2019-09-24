package com.addcel.PuntoWeb.bean;

import lombok.Data;

@Data
public class PuntoWebRequestDTO {
    private PuntoWebRequest request;
    private Integer idApp;
    private Integer idPais;
    private String idioma;
}
