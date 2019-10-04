package com.addcel.PuntoWeb.service;

import com.addcel.PuntoWeb.bean.FacPagoRequestDTO;
import com.addcel.PuntoWeb.exception.ServiceException;
import org.springframework.ui.Model;

public interface FacPagoService {

    /**
     * Send charge to 3D secure
     */
    void sendChargeTo3DSecure(FacPagoRequestDTO request, Model model) throws ServiceException;
}
