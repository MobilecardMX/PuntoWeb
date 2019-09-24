package com.addcel.PuntoWeb.repository;

import com.addcel.PuntoWeb.model.Establecimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface EstablecimientoRepository extends CrudRepository<Establecimiento, BigInteger> {
}
