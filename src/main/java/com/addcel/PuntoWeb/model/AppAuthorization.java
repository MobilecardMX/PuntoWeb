package com.addcel.PuntoWeb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APP_AUTHORIZATION")
public class AppAuthorization {
    @Id
    @Column(name = "id_app_auth")
    private Integer idAppAuth;
    @Column(name = "id_application")
    private String idApplication;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "activo")
    private String activo;

    public Integer getIdAppAuth() {
        return idAppAuth;
    }

    public void setIdAppAuth(Integer idAppAuth) {
        this.idAppAuth = idAppAuth;
    }

    public String getIdApplication() {
        return idApplication;
    }

    public void setIdApplication(String idApplication) {
        this.idApplication = idApplication;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
}
