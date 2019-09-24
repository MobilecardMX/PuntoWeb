package com.addcel.PuntoWeb.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Log4j
@Controller
public class FacPagoController {

    /**
     * View facPago.html
     */
    @GetMapping("pw-facpago")
    public ModelAndView facPagoView() {
        log.info("Load fac pago...");
        return new ModelAndView("facPago");
    }
}
