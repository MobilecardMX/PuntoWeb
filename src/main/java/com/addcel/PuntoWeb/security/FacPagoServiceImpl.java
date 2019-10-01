package com.addcel.PuntoWeb.security;

import com.addcel.PuntoWeb.bean.AttributeModelFacPago;
import com.addcel.PuntoWeb.bean.FacPagoRequest;
import com.addcel.PuntoWeb.config.PuntoWebConfig;
import com.addcel.PuntoWeb.model.TarjetasUsuario;
import com.addcel.PuntoWeb.repository.TarjetasUsuarioRepository;
import com.addcel.PuntoWeb.service.FacPagoService;
import com.addcel.PuntoWeb.util.Constantes;
import com.addcel.PuntoWeb.util.PuntoWebUtil;
import com.addcel.utils.AddcelCrypto;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
     * Send charge to 3D secure
     */
    @Override
    public void sendChargeTo3DSecure(FacPagoRequest request, Model model) {
        model.addAttribute(AttributeModelFacPago.MARCA_TARJETA.getName(), request.getMarcaTarjeta());
        model.addAttribute(AttributeModelFacPago.CODIGO_SUB_COMERCION.getName(), puntoWebConfig.getComercio());
//        model.addAttribute(AttributeModelFacPago.NUM_REFERENCIA.getName(), request.getMarcaTarjeta()); FIXME id_bitacora
        model.addAttribute(AttributeModelFacPago.MONTO_TRANSACCION.getName(), request.getMonto());
        model.addAttribute(AttributeModelFacPago.MONEDA_TRANSACCION.getName(), Constantes.MONEDA_PEN);

        // obtener info de la tarjeta de la base de datos
        Optional<TarjetasUsuario> tarjetasUsuario = Optional.ofNullable(tarjetasUsuarioRepository.findOne(request.getIdTarjetaUsuario()));
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
        model.addAttribute(AttributeModelFacPago.DIFERIDO.getName(), request.getDiferido());
        model.addAttribute(AttributeModelFacPago.CUOTAS.getName(), request.getCuotas());
        model.addAttribute(AttributeModelFacPago.CODIGO_COMERCIAL_FACILITADOR.getName(), puntoWebConfig.getComercio());
        model.addAttribute(AttributeModelFacPago.TIPO_PROCESO.getName(), Constantes.TIPO_PROCESO_AUTORIZACION);
        //model.addAttribute(AttributeModelFacPago.DATA_HASH.getName(), request.getMarcaTarjeta());
        //model.addAttribute(AttributeModelFacPago.FIRMA_DIGITAL.getName(), request.getMarcaTarjeta());
        model.addAttribute(Constantes.ACTION_FORM_PARAM, "https://server.punto-web.com/gateway/PWFacPagos.asp");
    }
}
