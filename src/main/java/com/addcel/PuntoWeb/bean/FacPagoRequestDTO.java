package com.addcel.PuntoWeb.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FacPagoRequestDTO extends AbstractRequestDTO {
    private FacPagoRequest request;
}
