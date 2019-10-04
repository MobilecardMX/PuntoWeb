package com.addcel.PuntoWeb.service;

import com.addcel.PuntoWeb.bean.FacPagoRequestDTO;
import com.addcel.PuntoWeb.bean.PuntoWebRequestDTO;
import com.addcel.PuntoWeb.model.PuntoWebBitacora;

public interface BitacoraService {

    /**
     * Guardar bitacora de transacciones t_bitacora y punto_web_bitacora
     *
     * @param puntoWebRequestDTO DTO que viene desde el controller
     */
    PuntoWebBitacora saveBitacoraPuntoWeb(PuntoWebRequestDTO puntoWebRequestDTO, String requestPuntoWeb, Integer idTBitacora);

    /**
     * Guardar bitacora de transacciones t_bitacora y punto_web_bitacora
     *
     * @param facPagoRequestDTO DTO que viene desde el controller
     */
    PuntoWebBitacora saveBitacoraPuntoWebForFacPago(FacPagoRequestDTO facPagoRequestDTO, String requestPuntoWeb, Integer idTBitacora);

    /**
     * Actualizar response punto web
     *
     * @param puntoWebBitacora bean con la entidad de bitacora
     * @param responsePuntoWeb respuesta del servicio Punto WEb
     */
    void updateResponsePuntoWeb(PuntoWebBitacora puntoWebBitacora, String responsePuntoWeb);

    /**
     * Guardar en t_bitacora
     */
    Integer saveTBitacora(PuntoWebRequestDTO puntoWebRequestDTO);


    /**
     * Guardar en t_bitacora para el flujo de 3D secure
     */
    Integer saveTBitacoraForFacPago(FacPagoRequestDTO facPagoRequestDTO);
}
