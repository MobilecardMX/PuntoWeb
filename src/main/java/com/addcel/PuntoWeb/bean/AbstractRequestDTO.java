package com.addcel.PuntoWeb.bean;

import lombok.Data;

@Data
public abstract class AbstractRequestDTO {
    private Integer idApp;
    private Integer idPais;
    private String idioma;
}
