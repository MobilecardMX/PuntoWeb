package com.addcel.PuntoWeb.model;


import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Entity()
@Table(name = "PUNTO_WEB_BITACORA")
@Data
public class PuntoWebBitacora {
    @Id
    @Column(name = "ID_BITACORA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBitacora;
    @Column(name = "FECHA")
    private Date fecha;
    @Column(name = "REQUEST_ENDPOINT")
    private String requestEndpoint;
    @Column(name = "RESPONSE_ENDPOINT")
    private String responseEndpoint;
    @Column(name = "REQUEST_PUNTO_WEB")
    private String requestPuntoWeb;
    @Column(name = "RESPONSE_PUNTO_WEB")
    private String responsePuntoWeb;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ID_APP")
    private Integer idApp;
    @Column(name = "ID_PAIS")
    private String idPais;
    @Column(name = "IDIOMA")
    private String idioma;
    @Column(name = "ID_T_BITACORA")
    private Integer idTBitacora;
}
