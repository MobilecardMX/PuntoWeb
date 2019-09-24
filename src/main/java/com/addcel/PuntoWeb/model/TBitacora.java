package com.addcel.PuntoWeb.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "t_bitacora")
public class TBitacora {

    @Id
    @Column(name = "id_bitacora")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBitacora;
    @Column(name = "id_usuario")
    private BigInteger idUsuario;
    @Column(name = "id_aplicacion")
    private Integer idAplicacion;
    @Column(name = "id_proveedor")
    private String idProveedor;
    @Column(name = "id_producto")
    private BigInteger idProducto;
    @Column(name = "idioma")
    private String idioma;
    @Column(name = "bit_hora")
    private Timestamp bitHora;
    @Column(name = "bit_fecha")
    private Date bitFecha;
    @Column(name = "bit_concepto")
    private String bitConcepto;
    @Column(name = "bit_cargo")
    private BigDecimal bitCargo;
    @Column(name = "bit_comision")
    private BigDecimal bitComision;
    @Column(name = "bit_card_id")
    private Integer bitCardId;
    @Column(name = "bit_referencia")
    private String bitReferencia;
    @Column(name = "bit_no_autorizacion")
    private String bitNoAutorizacion;
    @Column(name = "bit_codigo_error")
    private String bitCodigoError;
    @Column(name = "bit_status")
    private Integer bitStatus;
    @Column(name = "imei")
    private String imei;
    @Column(name = "destino")
    private String destino;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "software")
    private String software;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "wkey")
    private String wkey;
    @Column(name = "cy")
    private String cy;
    @Column(name = "cx")
    private String cx;
    @Column(name = "tarjeta_compra")
    private String tarjetaCompra;
    @Column(name = "bit_ticket")
    private String bitTicket;
    @Column(name = "pase")
    private Integer pase;
    @Column(name = "id_pais")
    private Integer idPais;
    @Column(name = "id_establecimiento")
    private BigInteger idEstablecimiento;
}
