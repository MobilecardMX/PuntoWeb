package com.addcel.PuntoWeb.repository;

import com.addcel.PuntoWeb.model.PuntoWebConfigParam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PuntoWebConfigParamRepository extends CrudRepository<PuntoWebConfigParam, Integer> {

    /**
     * buscar configuracion por key param
     *
     * @param keyParam key param en la tabla PUNTO_WEB_CONFIG_PARAM
     */
    Optional<PuntoWebConfigParam> findByKeyParam(String keyParam);
}
