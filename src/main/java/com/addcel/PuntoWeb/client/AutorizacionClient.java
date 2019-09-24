package com.addcel.PuntoWeb.client;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import puntoweb.autoriza.wsdl.Autoriza;
import puntoweb.autoriza.wsdl.AutorizaResponse;
import puntoweb.autoriza.wsdl.ObjectFactory;

@Log4j
public class AutorizacionClient extends WebServiceGatewaySupport {

    @Value("${com.addcel.PuntoWeb.client.ws.wsdl}")
    private String wsdlUrl;

    /**
     * Peticion al WS SOAP
     */
    public AutorizaResponse autorizacion(String xmlRequest) {
        log.info("send client WS SOAP autorizacion");
        AutorizaResponse response;
        Autoriza request = new Autoriza();
        ObjectFactory factory = new ObjectFactory();
        request.setXml(factory.createAutorizaXml(xmlRequest));

        response = (AutorizaResponse) getWebServiceTemplate().marshalSendAndReceive(
                wsdlUrl,
                request,
                new SoapActionCallback("http://tempuri.org/Isrvproceso/Autoriza"));

        return response;
    }
}
