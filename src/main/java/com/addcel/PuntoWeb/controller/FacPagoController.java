package com.addcel.PuntoWeb.controller;

import com.addcel.PuntoWeb.bean.FacPagoRequest;
import com.addcel.PuntoWeb.bean.FacPagoRequestDTO;
import com.addcel.PuntoWeb.bean.PuntoWebResponse;
import com.addcel.PuntoWeb.exception.ServiceException;
import com.addcel.PuntoWeb.service.FacPagoServiceImpl;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("{idApp}/{idPais}/{idioma}/pw-facpago")
    public ModelAndView facPagoView(
            @PathVariable("idApp") Integer idApp,
            @PathVariable("idPais") Integer idPais,
            @PathVariable("idioma") String idioma,
            @RequestBody FacPagoRequest request,
            Model model) {
        log.info("Load fac pago...");
        FacPagoRequestDTO facPagoRequestDTO = new FacPagoRequestDTO();
        facPagoRequestDTO.setRequest(request);
        facPagoRequestDTO.setIdApp(idApp);
        facPagoRequestDTO.setIdioma(idioma);
        facPagoRequestDTO.setIdPais(idPais);
        try {
            facPagoService.sendChargeTo3DSecure(facPagoRequestDTO, model);
        } catch (ServiceException e) {
            log.error("Ocurrio un error en el business", e);
            return new ModelAndView("error");
        }
        return new ModelAndView("facPago");
    }

    /**
     * Test para probar
     */
    @GetMapping("test")
    public ModelAndView test() {
        return new ModelAndView("test");
    }
}
