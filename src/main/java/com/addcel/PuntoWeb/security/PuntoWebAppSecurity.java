package com.addcel.PuntoWeb.security;

import com.addcel.PuntoWeb.config.CredentialConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class PuntoWebAppSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private CredentialConfig credentialConfig;
    private static final String DEFAULT_ROLE = "USER";
    private static final Logger LOGGER = LoggerFactory.getLogger(PuntoWebAppSecurity.class);
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * Configuracion Spring security autenticacion basic
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/facPago/**", "/pw-facpago/**").permitAll()
                .antMatchers("/authorization/**").authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);
    }

    /**
     * Validar usuario/contraseña en base de datos, para esta app
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.info("Cargando configuracion global de seguridad...");
        LOGGER.info("Username: " + credentialConfig.getUsername());
        auth.inMemoryAuthentication()
                .withUser(credentialConfig.getUsername())
                .password(credentialConfig.getPassword())
                .roles(DEFAULT_ROLE);
    }
}
