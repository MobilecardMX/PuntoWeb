package com.addcel.PuntoWeb.service;

import com.addcel.PuntoWeb.bean.PuntoWebRequestDTO;
import com.addcel.PuntoWeb.bean.ResponseServiceDTO;
import com.addcel.PuntoWeb.exception.DataInputException;
import com.addcel.PuntoWeb.exception.ServiceException;

import java.rmi.server.ServerCloneException;

public interface PuntoWebService {

    /**
     * Logica de negocio de la autorizacion de Punto Web
     *
     * @param puntoWebRequestDTO DTO con los objetos enviados desde el controller
     */
    ResponseServiceDTO authorization(PuntoWebRequestDTO puntoWebRequestDTO) throws DataInputException, ServiceException;
}
