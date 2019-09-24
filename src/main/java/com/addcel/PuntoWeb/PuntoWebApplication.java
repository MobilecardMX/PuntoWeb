package com.addcel.PuntoWeb;

import com.addcel.PuntoWeb.client.AutorizacionClient;
import com.addcel.PuntoWeb.config.CredentialConfig;
import com.addcel.PuntoWeb.config.PuntoWebConfig;
import com.addcel.PuntoWeb.model.AppAuthorization;
import com.addcel.PuntoWeb.model.PuntoWebConfigParam;
import com.addcel.PuntoWeb.repository.AppAuthorizationRepository;
import com.addcel.PuntoWeb.repository.PuntoWebConfigParamRepository;
import com.addcel.PuntoWeb.util.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.annotation.PostConstruct;
import java.util.Optional;

@SpringBootApplication
public class PuntoWebApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PuntoWebApplication.class);
    @Autowired
    private AppAuthorizationRepository appAuthorizationRepository;
    @Autowired
    private PuntoWebConfigParamRepository puntoWebConfigParamRepository;

    public static void main(String[] args) {
        SpringApplication.run(PuntoWebApplication.class, args);
    }

    /**
     * Configuración para desplegar en el servidor de aplicaciones, en este caso WildFly.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PuntoWebApplication.class);
    }

    /**
     * Bean que carga la configuración de la base de datos, de usuario y contraseña de la aplicacion
     */
    @Bean
    @PostConstruct
    public CredentialConfig credentialConfig() {
        LOGGER.info("Cargando configuracion de credenciales Pago Efectivo...");
        CredentialConfig credentialConfig = new CredentialConfig();
        Optional<AppAuthorization> appAuthorization = appAuthorizationRepository.findByIdApplicationAndActivo(Constantes.ID_APPLICATION, Constantes.ACTIVO);
        if (appAuthorization.isPresent()) {
            LOGGER.info(String.format("Credenciales ID Aplicacion %s, cargadas correctamente.", appAuthorization.get().getIdApplication()));
            credentialConfig.setUsername(appAuthorization.get().getUsername());
            credentialConfig.setPassword(appAuthorization.get().getPassword());
        } else {
            LOGGER.info("No fue posible cargar las credenciales de la aplicacion...");
        }
        return credentialConfig;
    }

    /**
     * Se inicializa la configuracion desde la base de datos del servicio
     */
    @Bean
    public PuntoWebConfig puntoWebConfigParam() {
        PuntoWebConfig puntoWebConfig = new PuntoWebConfig();

        Optional<PuntoWebConfigParam> comercioOpt = puntoWebConfigParamRepository.findByKeyParam(Constantes.COMERCIO_REQUEST);
        comercioOpt.ifPresent(comercio -> puntoWebConfig.setComercio(comercio.getValueParam()));

        Optional<PuntoWebConfigParam> marcaOpt = puntoWebConfigParamRepository.findByKeyParam(Constantes.MARCA_REQUEST);
        marcaOpt.ifPresent(marca -> puntoWebConfig.setMarca(marca.getValueParam()));

        Optional<PuntoWebConfigParam> comercioFacilitadorOpt = puntoWebConfigParamRepository.findByKeyParam(Constantes.COMERCIO_FACILITADOR_REQUEST);
        comercioFacilitadorOpt.ifPresent(comercioFacilitador -> puntoWebConfig.setComercioFacilitador(comercioFacilitador.getValueParam()));

        Optional<PuntoWebConfigParam> keyMerchantSolesOpt = puntoWebConfigParamRepository.findByKeyParam(Constantes.KEY_MERCHANT_SOLES);
        keyMerchantSolesOpt.ifPresent(keyMerchantSoles -> puntoWebConfig.setKeyMerchantSoles(keyMerchantSoles.getValueParam()));

        return puntoWebConfig;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("puntoweb.autoriza.wsdl");
        return marshaller;
    }

    @Bean
    public AutorizacionClient countryClient(Jaxb2Marshaller marshaller) {
        AutorizacionClient client = new AutorizacionClient();
        client.setDefaultUri("https://testws.punto-web.com/wcfEcommercePW/srvproceso.svc");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
