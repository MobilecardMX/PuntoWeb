package com.addcel.PuntoWeb.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PUNTO_WEB_CONFIG_PARAM")
public class PuntoWebConfigParam {
    @Id
    @Column(name = "ID_CONFIG_PARAM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConfigParam;
    @Column(name = "KEY_PARAM")
    private String keyParam;
    @Column(name = "VALUE_PARAM")
    private String valueParam;
}
