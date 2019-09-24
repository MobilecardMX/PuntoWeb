package com.addcel.PuntoWeb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.addcel.PuntoWeb.model.PuntoWebBitacora;

@Repository
public interface PuntoWebBitacoraRepository extends CrudRepository<PuntoWebBitacora, Integer> {

}
