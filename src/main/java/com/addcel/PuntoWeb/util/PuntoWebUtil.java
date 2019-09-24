package com.addcel.PuntoWeb.util;

import TDES.TDESBase64;
import com.addcel.PuntoWeb.client.request.Autorizacion;
import com.addcel.PuntoWeb.config.PuntoWebConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Log4j
public class PuntoWebUtil {

    /**
     * Pattern para la fecha request service SOAP Punto Web
     */
    private static final String FORMAT_FECHA = "yyyyMMdd";
    /**
     * Pattern para la hora request service SOAP Punto Web
     */
    private static final String FORMAT_HORA = "HHMMSS";

    /**
     * bean de configuracion desde base de datos
     */
    @Autowired
    private PuntoWebConfig puntoWebConfig;


    /**
     * Serializar bean de request del WS SOAP PuntoWeb, como XML
     *
     * @param autorizacion bean de request
     */
    public String serializeRequestToXML(final Autorizacion autorizacion) throws JsonProcessingException {
        log.debug("exec serializeRequestToXML()");
        String respose;
        XmlMapper xmlMapper = new XmlMapper();
        respose = xmlMapper.writeValueAsString(autorizacion);
        return respose;
    }


    /**
     * Deserializar XML a Objeto
     */
    public com.addcel.PuntoWeb.client.response.Autorizacion deserializarResponseToObject(final String xml) throws IOException, SOAPException, JAXBException {
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(xml.getBytes()));
        Unmarshaller unmarshaller = JAXBContext.newInstance(com.addcel.PuntoWeb.client.response.Autorizacion.class).createUnmarshaller();
        return (com.addcel.PuntoWeb.client.response.Autorizacion) unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
    }


    /**
     * obtener fecha con el formato solicitado por el servicio
     */
    public String getFechaRequestService() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_FECHA);
        return now.format(formatter);
    }

    /**
     * obtener fecha con el formato solicitado por el servicio
     */
    public String getHoraRequestService() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_HORA);
        return now.format(formatter);
    }

    /**
     * Cifrar cadena con clases de PuntoWeb
     *
     * @param cadena cadena a cifrar
     */
    public String encryptStringPMPTDES(String cadena) {
        TDESBase64 objTDESB64 = new TDESBase64(puntoWebConfig.getKeyMerchantSoles());
        return objTDESB64.Encripta(cadena);
    }

    /**
     * Completar el campo reference
     */
    public String getReferencia(Integer referencia) {
        if (referencia != null && String.valueOf(referencia).length() < 6) {
            return String.format("%06d", referencia);
        } else return String.valueOf(referencia);
    }

    /**
     * Valida los datos de la tarjeta
     *
     * @param ccv     CCV de la tarjeta
     * @param card    numero de tarjeta
     * @param dateExt fecha de expiracion de la tarjeta
     */
    public boolean isValidCardData(String ccv, String card, String dateExt) {
        log.info("exec isValidCardData");
        if (StringUtils.isEmpty(ccv) || StringUtils.isEmpty(card) || StringUtils.isEmpty(dateExt)) {
            return false;
        }
        return true;
    }
}
