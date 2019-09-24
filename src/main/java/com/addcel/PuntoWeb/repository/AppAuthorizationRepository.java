package com.addcel.PuntoWeb.repository;

import com.addcel.PuntoWeb.model.AppAuthorization;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppAuthorizationRepository extends CrudRepository<AppAuthorization, Integer> {

    /**
     * Leer usuario/contraseña de la BD, por idApp y si esta activo.
     *
     * @param idApplication Id de la aplicacion
     * @param activo        Bandera para determinar si esta activo o no, T=Actvo, F=No activo
     */
    Optional<AppAuthorization> findByIdApplicationAndActivo(String idApplication, String activo);
}
