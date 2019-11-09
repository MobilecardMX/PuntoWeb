package com.addcel.PuntoWeb.service;

import com.addcel.PuntoWeb.bean.FacPagoRequestDTO;
import com.addcel.PuntoWeb.bean.PuntoWebRequestDTO;
import com.addcel.PuntoWeb.model.PuntoWebBitacora;
import com.addcel.PuntoWeb.model.TBitacora;
import com.addcel.PuntoWeb.repository.PuntoWebBitacoraRepository;
import com.addcel.PuntoWeb.repository.TBitacoraRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Service
@Log4j
public class BitacoraServiceImpl implements BitacoraService {

    /**
     * Repository to save in PUNTO_WEB_BITACORA
     */
    @Autowired
    private PuntoWebBitacoraRepository bitacoraRepository;

    /**
     * Repository to sabe T_BITACORA
     */
    @Autowired
    private TBitacoraRepository tBitacoraRepository;

    // #### CONSTANTES ####
    private static final String ID_PROVEEDOR = "700";
    private static final BigInteger ID_PRODUCTO = new BigInteger("7100");

    private static final String DESCRIPCION_BITACORA = "Peticion de autorizacion al servicio Punto Web";

    /**
     * Guardar bitacora
     *
     * @param puntoWebRequestDTO DTO que viene desde el controller
     * @param requestPuntoWeb    Request del servicio SOAP Punto Web
     */
    @Override
    public PuntoWebBitacora saveBitacoraPuntoWeb(PuntoWebRequestDTO puntoWebRequestDTO, String requestPuntoWeb, Integer idTBitacora) {
        log.info("Guardando bitacora en PUNTO_WEB_BITACORA...");
        PuntoWebBitacora puntoWebBitacora = new PuntoWebBitacora();
        puntoWebBitacora.setFecha(new Date());
        puntoWebBitacora.setDescripcion(BitacoraServiceImpl.DESCRIPCION_BITACORA);
        puntoWebBitacora.setIdApp(puntoWebRequestDTO.getIdApp());
        puntoWebBitacora.setIdioma(puntoWebRequestDTO.getIdioma());
        puntoWebBitacora.setIdPais(String.valueOf(puntoWebRequestDTO.getIdPais()));
        puntoWebBitacora.setRequestEndpoint(puntoWebRequestDTO.toString());
        puntoWebBitacora.setRequestPuntoWeb(requestPuntoWeb);
        puntoWebBitacora.setIdTBitacora(idTBitacora);
        //guardar en t_bitacora
        bitacoraRepository.save(puntoWebBitacora);
        return puntoWebBitacora;
    }

    @Override
    public PuntoWebBitacora saveBitacoraPuntoWebForFacPago(FacPagoRequestDTO facPagoRequestDTO, String requestPuntoWeb, Integer idTBitacora) {
        log.info("Guardando bitacora en PUNTO_WEB_BITACORA...");
        PuntoWebBitacora puntoWebBitacora = new PuntoWebBitacora();
        puntoWebBitacora.setFecha(new Date());
        puntoWebBitacora.setDescripcion(BitacoraServiceImpl.DESCRIPCION_BITACORA);
        puntoWebBitacora.setIdApp(facPagoRequestDTO.getIdApp());
        puntoWebBitacora.setIdioma(facPagoRequestDTO.getIdioma());
        puntoWebBitacora.setIdPais(String.valueOf(facPagoRequestDTO.getIdPais()));
        puntoWebBitacora.setRequestEndpoint(facPagoRequestDTO.toString());
        puntoWebBitacora.setRequestPuntoWeb(requestPuntoWeb);
        puntoWebBitacora.setIdTBitacora(idTBitacora);
        //guardar en t_bitacora
        bitacoraRepository.save(puntoWebBitacora);
        return puntoWebBitacora;
    }

    /**
     * Actualizar response punto web
     *
     * @param puntoWebBitacora bean con la entidad de bitacora
     * @param responsePuntoWeb respuesta del servicio Punto WEb
     */
    @Override
    public void updateResponsePuntoWeb(PuntoWebBitacora puntoWebBitacora, String responsePuntoWeb) {
        puntoWebBitacora.setResponsePuntoWeb(responsePuntoWeb);
        bitacoraRepository.save(puntoWebBitacora);
    }

    @Override
    public TBitacora saveTBitacora(PuntoWebRequestDTO puntoWebRequestDTO) {
        log.info("Guardando bitacora en t_bitacora...");
        TBitacora tBitacora = new TBitacora();
        try {
            tBitacora.setIdUsuario(puntoWebRequestDTO.getRequest().getIdUsuario());
            tBitacora.setIdAplicacion(puntoWebRequestDTO.getIdApp());
            tBitacora.setIdioma(puntoWebRequestDTO.getIdioma());
            tBitacora.setIdProveedor(ID_PROVEEDOR);
            tBitacora.setIdProducto(ID_PRODUCTO);
            tBitacora.setBitFecha(new Date());
            tBitacora.setBitHora(new Timestamp(new Date().getTime()));
            tBitacora.setBitConcepto(puntoWebRequestDTO.getRequest().getConcepto());
            tBitacora.setBitCargo(puntoWebRequestDTO.getRequest().getMonto() == null ? BigDecimal.ZERO : new BigDecimal(puntoWebRequestDTO.getRequest().getMonto()));
            tBitacora.setBitComision(puntoWebRequestDTO.getRequest().getComision() == null ? BigDecimal.ZERO : new BigDecimal(puntoWebRequestDTO.getRequest().getComision()));
            tBitacora.setBitCardId(puntoWebRequestDTO.getRequest().getIdTarjetaUsuario());
            tBitacora.setIdPais(puntoWebRequestDTO.getIdPais());
            tBitacora.setIdEstablecimiento(puntoWebRequestDTO.getRequest().getIdEstablecimiento() == null ? BigInteger.ZERO : puntoWebRequestDTO.getRequest().getIdEstablecimiento());
            tBitacoraRepository.save(tBitacora);
            log.debug("Se guardo con EXITO la bitacora!!!");
            return tBitacora;
        } catch (RuntimeException ex) {
            log.error("Ocurrio un error al guardar en T_BITACORA, mensaje: " + ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public TBitacora updateTBitacora(TBitacora tBitacora) {
        return tBitacoraRepository.save(tBitacora);
    }

    @Override
    public Integer saveTBitacoraForFacPago(FacPagoRequestDTO facPagoRequestDTO) {
        log.info("Guardando bitacora en t_bitacora...");
        TBitacora tBitacora = new TBitacora();
        try {
            tBitacora.setIdUsuario(facPagoRequestDTO.getRequest().getIdUsuario());
            tBitacora.setIdAplicacion(facPagoRequestDTO.getIdApp());
            tBitacora.setIdioma(facPagoRequestDTO.getIdioma());
            tBitacora.setIdProveedor(ID_PROVEEDOR);
            tBitacora.setIdProducto(ID_PRODUCTO);
            tBitacora.setBitFecha(new Date());
            tBitacora.setBitHora(new Timestamp(new Date().getTime()));
            tBitacora.setBitConcepto(facPagoRequestDTO.getRequest().getConcepto());
            tBitacora.setBitCargo(facPagoRequestDTO.getRequest().getMonto() == null ? BigDecimal.ZERO : new BigDecimal(facPagoRequestDTO.getRequest().getMonto()));
            tBitacora.setBitComision(StringUtils.isEmpty(facPagoRequestDTO.getRequest().getComision()) ? BigDecimal.ZERO : new BigDecimal(facPagoRequestDTO.getRequest().getComision()));
            tBitacora.setBitCardId(facPagoRequestDTO.getRequest().getIdTarjetaUsuario());
            tBitacora.setIdPais(facPagoRequestDTO.getIdPais());
            tBitacora.setIdEstablecimiento(facPagoRequestDTO.getRequest().getIdEstablecimiento() == null ? BigInteger.ZERO : facPagoRequestDTO.getRequest().getIdEstablecimiento());
            tBitacoraRepository.save(tBitacora);
            log.debug("Se guardo con EXITO la bitacora!!!");
            return tBitacora.getIdBitacora();
        } catch (RuntimeException ex) {
            log.error("Ocurrio un error al guardar en T_BITACORA, mensaje: " + ex.getMessage(), ex);
            return -1;
        }
    }

}
