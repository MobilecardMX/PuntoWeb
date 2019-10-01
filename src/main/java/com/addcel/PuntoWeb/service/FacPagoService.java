package com.addcel.PuntoWeb.service;

import com.addcel.PuntoWeb.bean.FacPagoRequest;
import org.springframework.ui.Model;

public interface FacPagoService {

    /**
     * Send charge to 3D secure
     */
    void sendChargeTo3DSecure(FacPagoRequest request, Model model);
}
