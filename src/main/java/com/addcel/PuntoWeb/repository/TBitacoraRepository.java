package com.addcel.PuntoWeb.repository;

import com.addcel.PuntoWeb.model.TBitacora;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TBitacoraRepository extends CrudRepository<TBitacora, Integer> {
}
