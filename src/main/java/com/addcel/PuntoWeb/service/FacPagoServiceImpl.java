package com.addcel.PuntoWeb.service;

import com.addcel.PuntoWeb.bean.AttributeModelFacPago;
import com.addcel.PuntoWeb.bean.FacPagoRequestDTO;
import com.addcel.PuntoWeb.config.PuntoWebConfig;
import com.addcel.PuntoWeb.exception.ServiceException;
import com.addcel.PuntoWeb.model.TarjetasUsuario;
import com.addcel.PuntoWeb.repository.TarjetasUsuarioRepository;
import com.addcel.PuntoWeb.util.Constantes;
import com.addcel.PuntoWeb.util.Mensajes;
import com.addcel.PuntoWeb.util.PuntoWebUtil;
import com.addcel.utils.AddcelCrypto;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Base64;
import java.util.Optional;

@Service
@Log4j
public class FacPagoServiceImpl implements FacPagoService {

    /**
     * repository de la entidad
     */
    @Autowired
    private TarjetasUsuarioRepository tarjetasUsuarioRepository;

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
     * Servicio para guardar/actualizar bitacora
     */
    @Autowired
    private BitacoraService bitacoraService;

    /**
     * Send charge to 3D secure
     */
    @Override
    public void sendChargeTo3DSecure(FacPagoRequestDTO facPagoRequestDTO, Model model) throws ServiceException {

        //se guarda en T_BITACORA
        Integer idTBitacora = bitacoraService.saveTBitacoraForFacPago(facPagoRequestDTO);
        if (idTBitacora > 0) {

            model.addAttribute(AttributeModelFacPago.MARCA_TARJETA.getName(), facPagoRequestDTO.getRequest().getMarcaTarjeta());
            model.addAttribute(AttributeModelFacPago.CODIGO_SUB_COMERCION.getName(), puntoWebConfig.getComercio());
            model.addAttribute(AttributeModelFacPago.NUM_REFERENCIA.getName(), StringUtils.rightPad(String.valueOf(idTBitacora), 6, "0"));
            model.addAttribute(AttributeModelFacPago.MONTO_TRANSACCION.getName(), puntoWebUtil.encryptStringPMPTDES(facPagoRequestDTO.getRequest().getMonto()));
            model.addAttribute(AttributeModelFacPago.MONEDA_TRANSACCION.getName(), Constantes.MONEDA_PEN);

            // obtener info de la tarjeta de la base de datos
            Optional<TarjetasUsuario> tarjetasUsuario = Optional.ofNullable(tarjetasUsuarioRepository.findOne(facPagoRequestDTO.getRequest().getIdTarjetaUsuario()));
            if (tarjetasUsuario.isPresent()) {
                if (puntoWebUtil.isValidCardData(tarjetasUsuario.get().getCt(), tarjetasUsuario.get().getNumeroTarjeta(), tarjetasUsuario.get().getVigencia())) {
                    String tarjeta = AddcelCrypto.decryptTarjeta(tarjetasUsuario.get().getNumeroTarjeta() != null ? tarjetasUsuario.get().getNumeroTarjeta() : "");
                    model.addAttribute(AttributeModelFacPago.TARJETA_CREDITO.getName(), puntoWebUtil.encryptStringPMPTDES(tarjeta));

                    String cvc = AddcelCrypto.decryptTarjeta(tarjetasUsuario.get().getCt() != null ? tarjetasUsuario.get().getCt() : "");
                    model.addAttribute(AttributeModelFacPago.CVC2.getName(), puntoWebUtil.encryptStringPMPTDES(cvc));

                    String fechaExp = AddcelCrypto.decryptTarjeta(tarjetasUsuario.get().getVigencia() != null ? tarjetasUsuario.get().getVigencia() : "");
                    model.addAttribute(AttributeModelFacPago.FECHA_EXPIRACION_TARJETA.getName(), puntoWebUtil.encryptStringPMPTDES(fechaExp.replaceAll("/", "")));
                }
            }

            model.addAttribute(AttributeModelFacPago.FECHA_TRANSACCION.getName(), puntoWebUtil.getFechaRequestService());
            model.addAttribute(AttributeModelFacPago.HORA_TRANSACCION.getName(), puntoWebUtil.getHoraRequestService());
            model.addAttribute(AttributeModelFacPago.AUTOGENERADOR.getName(), System.currentTimeMillis());
            model.addAttribute(AttributeModelFacPago.DIFERIDO.getName(), facPagoRequestDTO.getRequest().getDiferido());
            model.addAttribute(AttributeModelFacPago.CUOTAS.getName(), facPagoRequestDTO.getRequest().getCuotas());
            model.addAttribute(AttributeModelFacPago.CODIGO_COMERCIAL_FACILITADOR.getName(), puntoWebConfig.getComercioFacPagoRequest());
            model.addAttribute(AttributeModelFacPago.TIPO_PROCESO.getName(), Constantes.TIPO_PROCESO_AUTORIZACION);
            model.addAttribute(AttributeModelFacPago.DATA_HASH.getName(), Base64.getEncoder().encode(model.toString().getBytes()));
            model.addAttribute(AttributeModelFacPago.FIRMA_DIGITAL.getName(), Base64.getEncoder().encode("Mobilcard".getBytes()));
            model.addAttribute(Constantes.ACTION_FORM_PARAM, "https://server.punto-web.com/gateway/PWFacPagos.asp");

            bitacoraService.saveBitacoraPuntoWebForFacPago(facPagoRequestDTO, model.toString(), idTBitacora);
        } else {
            throw new ServiceException("Ocurrio un error al guardar en la Bitacora", Mensajes.CODE_ERROR_TBITACORA);
        }


    }
}
