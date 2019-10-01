package com.addcel.PuntoWeb.controller;

import com.addcel.PuntoWeb.bean.FacPagoRequest;
import com.addcel.PuntoWeb.security.FacPagoServiceImpl;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Log4j
@Controller
public class FacPagoController {

    @Autowired
    private FacPagoServiceImpl facPagoService;

    /**
     * View facPago.html
     */
    @GetMapping("pw-facpago")
    public ModelAndView facPagoView(@RequestBody FacPagoRequest request, Model model) {
        log.info("Load fac pago...");
        facPagoService.sendChargeTo3DSecure(request, model);
        return new ModelAndView("facPago");
    }
}
