package com.addcel.PuntoWeb.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Data
@Entity
@Table(name = "LCPF_establecimiento")
public class Establecimiento {
    @Id
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "CODIGO_MC")
    private String codigoMc;
}
