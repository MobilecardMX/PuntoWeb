package com.addcel.PuntoWeb.client.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Autorizacion")
public class Autorizacion {
    private String marca;
    private String resultado;
    private String codAutoriza;
    private String referenciaPMP;
    private String cuotas;
    private String fecPrimeraCuota;
    private String monedaCuota;
    private String montoCuota;
    private String moneda;
    private String monto;
    private String referencia;
    private String fecha;
    private String hora;
    private String codRespuesta;
    private String codPais;
    private String tarjeta;
    private String mensaje;
    private String idTxn;
    private String comercio;
    private String dataEnc;
    private String firma;

    public Autorizacion() {
    }

    @XmlElement(name="Marca")
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @XmlElement(name="Resultado")
    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @XmlElement(name="CodAutoriza")
    public String getCodAutoriza() {
        return codAutoriza;
    }

    public void setCodAutoriza(String codAutoriza) {
        this.codAutoriza = codAutoriza;
    }

    @XmlElement(name="ReferenciaPMP")
    public String getReferenciaPMP() {
        return referenciaPMP;
    }

    public void setReferenciaPMP(String referenciaPMP) {
        this.referenciaPMP = referenciaPMP;
    }

    @XmlElement(name="Cuotas")
    public String getCuotas() {
        return cuotas;
    }

    public void setCuotas(String cuotas) {
        this.cuotas = cuotas;
    }

    @XmlElement(name="FecPrimeraCuota")
    public String getFecPrimeraCuota() {
        return fecPrimeraCuota;
    }

    public void setFecPrimeraCuota(String fecPrimeraCuota) {
        this.fecPrimeraCuota = fecPrimeraCuota;
    }

    @XmlElement(name="MonedaCuota")
    public String getMonedaCuota() {
        return monedaCuota;
    }

    public void setMonedaCuota(String monedaCuota) {
        this.monedaCuota = monedaCuota;
    }

    @XmlElement(name="MontoCuota")
    public String getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(String montoCuota) {
        this.montoCuota = montoCuota;
    }

    @XmlElement(name="Moneda")
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @XmlElement(name="Monto")
    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    @XmlElement(name="Referencia")
    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @XmlElement(name="Fecha")
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @XmlElement(name="Hora")
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @XmlElement(name="CodRespuesta")
    public String getCodRespuesta() {
        return codRespuesta;
    }

    public void setCodRespuesta(String codRespuesta) {
        this.codRespuesta = codRespuesta;
    }

    @XmlElement(name="CodPais")
    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    @XmlElement(name="Tarjeta")
    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    @XmlElement(name="Mensaje")
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @XmlElement(name="IdTxn")
    public String getIdTxn() {
        return idTxn;
    }

    public void setIdTxn(String idTxn) {
        this.idTxn = idTxn;
    }

    @XmlElement(name="Comercio")
    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    @XmlElement(name="DataEnc")
    public String getDataEnc() {
        return dataEnc;
    }

    public void setDataEnc(String dataEnc) {
        this.dataEnc = dataEnc;
    }

    @XmlElement(name="Firma")
    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    @Override
    public String toString() {
        return "Autorizacion{" +
                "marca='" + marca + '\'' +
                ", resultado='" + resultado + '\'' +
                ", codAutoriza='" + codAutoriza + '\'' +
                ", referenciaPMP='" + referenciaPMP + '\'' +
                ", cuotas='" + cuotas + '\'' +
                ", fecPrimeraCuota='" + fecPrimeraCuota + '\'' +
                ", monedaCuota='" + monedaCuota + '\'' +
                ", montoCuota='" + montoCuota + '\'' +
                ", moneda='" + moneda + '\'' +
                ", monto='" + monto + '\'' +
                ", referencia='" + referencia + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", codRespuesta='" + codRespuesta + '\'' +
                ", codPais='" + codPais + '\'' +
                ", tarjeta='" + tarjeta + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", idTxn='" + idTxn + '\'' +
                ", comercio='" + comercio + '\'' +
                ", dataEnc='" + dataEnc + '\'' +
                ", firma='" + firma + '\'' +
                '}';
    }
}
