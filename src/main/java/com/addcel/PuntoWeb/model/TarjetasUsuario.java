package com.addcel.PuntoWeb.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tarjetas_usuario")
@Data
public class TarjetasUsuario {
    @Id
    @Column(name = "idtarjetasusuario")
    private Integer idTarjetaUsuario;
    @Column(name = "numerotarjeta")
    private String numeroTarjeta;
    @Column(name = "vigencia")
    private String vigencia;
    @Column(name = "ct")
    private String ct;
}
