package com.addcel.PuntoWeb.controller;

import com.addcel.PuntoWeb.bean.PuntoWebRequest;
import com.addcel.PuntoWeb.bean.PuntoWebRequestDTO;
import com.addcel.PuntoWeb.bean.PuntoWebResponse;
import com.addcel.PuntoWeb.bean.ResponseServiceDTO;
import com.addcel.PuntoWeb.exception.DataInputException;
import com.addcel.PuntoWeb.exception.ServiceException;
import com.addcel.PuntoWeb.service.PuntoWebService;
import com.addcel.PuntoWeb.util.Mensajes;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
@Log4j
public class PuntoWebController {

    /**
     * objeto con la logica de negocio de la autorizacion de pago de Punto Web
     */
    @Autowired
    private PuntoWebService puntoWebService;

    /**
     * Obtener autorizacion de pago en con el WS SOAP de Punto Web
     */
    @PostMapping(value = "{idApp}/{idPais}/{idioma}/authorization")
    public ResponseEntity authorization(
            @PathVariable("idApp") Integer idApp,
            @PathVariable("idPais") Integer idPais,
            @PathVariable("idioma") String idioma,
            @RequestBody PuntoWebRequest request
    ) {
        log.info("init request /authorization request: " + request.toString());
        try {
            PuntoWebRequestDTO puntoWebRequestDTO = new PuntoWebRequestDTO();
            puntoWebRequestDTO.setIdApp(idApp);
            puntoWebRequestDTO.setIdioma(idioma);
            puntoWebRequestDTO.setIdPais(idPais);
            puntoWebRequestDTO.setRequest(request);

            ResponseServiceDTO response = puntoWebService.authorization(puntoWebRequestDTO);

            if(response.isExito()){
                return new ResponseEntity(new PuntoWebResponse(Mensajes.CODE_EXITO, Mensajes.MESSAGE_EXITO, response), HttpStatus.OK);
            } else {
                return new ResponseEntity(new PuntoWebResponse(Mensajes.CODE_ERROR_WS_CLIENT, response.getMessage()), HttpStatus.BAD_REQUEST);
            }


        } catch (DataInputException e) {
            return new ResponseEntity(new PuntoWebResponse(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ServiceException e) {
            return new ResponseEntity(new PuntoWebResponse(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
