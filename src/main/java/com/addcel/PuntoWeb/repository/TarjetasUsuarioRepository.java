package com.addcel.PuntoWeb.repository;

import com.addcel.PuntoWeb.model.TarjetasUsuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetasUsuarioRepository extends CrudRepository<TarjetasUsuario, Integer> {
}
