package com.addcel.PuntoWeb.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PuntoWebRequestDTO extends AbstractRequestDTO{
    private PuntoWebRequest request;
}
