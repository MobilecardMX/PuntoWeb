package com.addcel.PuntoWeb.service;

import java.io.IOException;
import java.util.Optional;

import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;

import com.addcel.PuntoWeb.model.Establecimiento;
import com.addcel.PuntoWeb.repository.EstablecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addcel.PuntoWeb.bean.PuntoWebRequest;
import com.addcel.PuntoWeb.bean.PuntoWebRequestDTO;
import com.addcel.PuntoWeb.bean.ResponseServiceDTO;
import com.addcel.PuntoWeb.client.AutorizacionClient;
import com.addcel.PuntoWeb.client.request.Autorizacion;
import com.addcel.PuntoWeb.config.PuntoWebConfig;
import com.addcel.PuntoWeb.exception.DataInputException;
import com.addcel.PuntoWeb.exception.ServiceException;
import com.addcel.PuntoWeb.model.PuntoWebBitacora;
import com.addcel.PuntoWeb.model.TarjetasUsuario;
import com.addcel.PuntoWeb.repository.TarjetasUsuarioRepository;
import com.addcel.PuntoWeb.util.Constantes;
import com.addcel.PuntoWeb.util.Mensajes;
import com.addcel.PuntoWeb.util.PuntoWebUtil;
import com.addcel.utils.AddcelCrypto;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.log4j.Log4j;
import puntoweb.autoriza.wsdl.AutorizaResponse;

@Service
@Log4j
public class PuntoWebServiceImpl implements PuntoWebService {

    /**
     * componente de utileria
     */
    @Autowired
    private PuntoWebUtil puntoWebUtil;
    /**
     * bean de configuracion desde base de datos
     */
    @Autowired
    private PuntoWebConfig puntoWebConfig;
    /**
     * repository de la entidad
     */
    @Autowired
    private TarjetasUsuarioRepository tarjetasUsuarioRepository;
    /**
     * Cliente del web service
     */
    @Autowired
    private AutorizacionClient autorizacionClient;

    /**
     * Servicio para guardar/actualizar bitacora
     */
    @Autowired
    private BitacoraService bitacoraService;

    /**
     * Repository de la entidad LCPF_establecimiento
     */
    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    /**
     * metodo de negocio, para reaizar el llamado el WS SOAP
     *
     * @param puntoWebRequestDTO DTO con los objetos enviados desde el controller
     */
    public ResponseServiceDTO authorization(PuntoWebRequestDTO puntoWebRequestDTO)
            throws DataInputException, ServiceException {
        log.info("init service authorization...");
        log.info("config param: " + puntoWebConfig.toString());
        ResponseServiceDTO responseServiceDTO = new ResponseServiceDTO();

        log.debug("Validando LCPF_establecimiento.CODIGO_MC, para el id: " + puntoWebRequestDTO.getRequest().getIdEstablecimiento());
        //validar CODIGO_MC
        Establecimiento establecimiento = establecimientoRepository.findOne(puntoWebRequestDTO.getRequest().getIdEstablecimiento());
        if (establecimiento == null) {
            throw new DataInputException(Mensajes.MESSAGE_DONT_CODIGO_MC, Mensajes.CODE_DONT_CODIGO_MC);
        }
        log.debug("Se encontro el CODIGO_MC: " + establecimiento.getCodigoMc());

        //se guarda en T_BITACORA
        Integer idTBitacora = bitacoraService.saveTBitacora(puntoWebRequestDTO);
        if (idTBitacora > 0) {
            //se guardo con exito en T_BITACORA, continua el flujo
            PuntoWebRequest request = puntoWebRequestDTO.getRequest();
            Autorizacion autorizacionBeanXML = new Autorizacion();
            // Obtener parametros del request configurados por base de datos
            autorizacionBeanXML.setMarca(puntoWebConfig.getMarca());
            autorizacionBeanXML.setComercio(puntoWebConfig.getComercio());
            autorizacionBeanXML.setReferencia(puntoWebUtil.getReferencia(idTBitacora));
            autorizacionBeanXML.setMonto(puntoWebUtil.encryptStringPMPTDES(request.getMonto())); // cifrar monto
            autorizacionBeanXML.setMoneda(request.getMoneda());

            // validar si la autorizacion es manual, osea que manda la info de la tarjeta,
            // fecha y ccv
            log.info("Autorzacion manual? " + request.isAutorizacionManual());
            if (request.isAutorizacionManual()) {
                log.debug("Cifrando informacion de la tarjeta...");
                if (puntoWebUtil.isValidCardData(request.getCcv(), request.getNumeroTarjeta(), request.getFechaVigencia())) {
                    String tarjeta = AddcelCrypto.decryptTarjeta(request.getNumeroTarjeta() != null ? request.getNumeroTarjeta() : "");
                    String cvc = AddcelCrypto.decryptTarjeta(request.getCcv() != null ? request.getCcv() : "");
                    String fechaExp = AddcelCrypto.decryptTarjeta(request.getFechaVigencia() != null ? request.getFechaVigencia() : "");

                    autorizacionBeanXML.setTarjeta(puntoWebUtil.encryptStringPMPTDES(tarjeta)); // cifrar tarjeta
                    autorizacionBeanXML.setCvc(puntoWebUtil.encryptStringPMPTDES(cvc)); // cifrar CVC
                    autorizacionBeanXML.setFechaExp(puntoWebUtil.encryptStringPMPTDES(fechaExp.replaceAll("/", "")));
                } else {
                    throw new DataInputException(Mensajes.MESSAGE_CARD_DONT_VALID, Mensajes.CODE_CARD_DONT_VALID);
                }

            } else {
                log.debug("Obteniendo informacion de la tarjeta de la DB...");
                // obtener info de la tarjeta de la base de datos
                Optional<TarjetasUsuario> tarjetasUsuario = Optional.ofNullable(tarjetasUsuarioRepository.findOne(request.getIdTarjetaUsuario()));
                if (tarjetasUsuario.isPresent()) {
                    log.info("info tarjeta: " + tarjetasUsuario.toString());
                    if (puntoWebUtil.isValidCardData(tarjetasUsuario.get().getCt(), tarjetasUsuario.get().getNumeroTarjeta(), tarjetasUsuario.get().getVigencia())) {
                        // decifrar info de la tarjeta
                        String tarjeta = AddcelCrypto.decryptTarjeta(tarjetasUsuario.get().getNumeroTarjeta() != null ? tarjetasUsuario.get().getNumeroTarjeta() : "");
                        autorizacionBeanXML.setTarjeta(puntoWebUtil.encryptStringPMPTDES(tarjeta)); // cifrar tarjeta

                        String cvc = AddcelCrypto.decryptTarjeta(tarjetasUsuario.get().getCt() != null ? tarjetasUsuario.get().getCt() : "");
                        autorizacionBeanXML.setCvc(puntoWebUtil.encryptStringPMPTDES(cvc)); // cifrar CVC

                        String fechaExp = AddcelCrypto.decryptTarjeta(tarjetasUsuario.get().getVigencia() != null ? tarjetasUsuario.get().getVigencia() : "");
                        log.info("fechaExp:" + fechaExp.replaceAll("/", ""));
                        autorizacionBeanXML.setFechaExp(puntoWebUtil.encryptStringPMPTDES(fechaExp.replaceAll("/", "")));
                    } else {
                        throw new DataInputException(Mensajes.MESSAGE_CARD_DONT_VALID, Mensajes.CODE_CARD_DONT_VALID);
                    }

                } else {
                    log.info("No se encontro informacion de la tarjeta, con el id: " + request.getIdTarjetaUsuario());
                    throw new DataInputException(Mensajes.MESSAGE_NO_DATA_CARD, Mensajes.CODE_NO_DATA_CARD);
                }
            }

            autorizacionBeanXML.setFecha(puntoWebUtil.getFechaRequestService());
            autorizacionBeanXML.setHora(puntoWebUtil.getHoraRequestService());

            autorizacionBeanXML.setDiferido(request.getDiferido());
            autorizacionBeanXML.setCuotas(request.getCuotas());
            autorizacionBeanXML.setTipoProceso(request.getTipoProceso());
            autorizacionBeanXML.setAutogenerador(request.getAutogenerador());
            autorizacionBeanXML.setComercioFacilitador(puntoWebConfig.getComercioFacilitador());
            autorizacionBeanXML.setDataEnc(request.getDataEnc());
            autorizacionBeanXML.setFirma(request.getFirma());

            // serializar objeto como XML, para enviarlo como parametro al cliente del WS
            // SAOP
            try {
                String xmlRequest = puntoWebUtil.serializeRequestToXML(autorizacionBeanXML);
                // guardar en bitacora
                PuntoWebBitacora bitacora = bitacoraService.saveBitacoraPuntoWeb(puntoWebRequestDTO, xmlRequest, idTBitacora);
                AutorizaResponse autorizaResponse = autorizacionClient.autorizacion(xmlRequest);

                // deserializar XML de respuesta a Objeto Java
                String xmlResponse = autorizaResponse.getAutorizaResult().getValue();
                // actualizar bitacora con la respuesta del servicio
                bitacoraService.updateResponsePuntoWeb(bitacora, xmlResponse);

                com.addcel.PuntoWeb.client.response.Autorizacion response = puntoWebUtil.deserializarResponseToObject(xmlResponse);
                log.info("response client BEAN: " + response.toString());

                // validar el codigo de respuesta de PuntoWeb
                if (Constantes.CODE_RESPUESTA_EXITO.equals(response.getCodRespuesta())) {
                    responseServiceDTO.setAutorizacion(response);
                } else {
                    responseServiceDTO.setExito(false);
                    responseServiceDTO.setMessage(response.getMensaje());
                }
                return responseServiceDTO;

            } catch (JsonProcessingException e) {
                log.error("Ocurrio un error al serializar el parametro xml del WS SAOP del WebMethod: Autoriza", e);
                throw new ServiceException(
                        "Ocurrio un error al serializar el parametro xml del WS SAOP del WebMethod: Autoriza",
                        Mensajes.CODE_SOAP_EX);
            } catch (IOException e) {
                log.error("Ocurrio un error al deserializar el XML de respuesta", e);
                throw new ServiceException("Ocurrio un error al deserializar el XML de respuesta", Mensajes.CODE_SOAP_EX);
            } catch (SOAPException e) {
                throw new ServiceException("Ocurrio un error en el cliente SOAP", Mensajes.CODE_SOAP_EX);
            } catch (JAXBException e) {
                throw new ServiceException("Ocurrio un error procesando XML", Mensajes.CODE_SOAP_EX);
            }
        } else {
            throw new ServiceException("Ocurrio un error al guardar en la Bitacora", Mensajes.CODE_ERROR_TBITACORA);
        }
    }
}
