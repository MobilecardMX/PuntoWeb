package com.addcel.PuntoWeb.util;

public interface Mensajes {
    //Mensajes de ERROR
    Integer CODE_NO_DATA_CARD = -1;
    String MESSAGE_NO_DATA_CARD = "Verifique los datos de su tarjeta.";
    Integer CODE_EXITO = 0;
    String MESSAGE_EXITO = "Transaccion realizada con exito";

    String MESSAGE_DONT_CODIGO_MC = "Comercio no está afiliado a Mastercard o está en proceso de afiliación";
    Integer CODE_DONT_CODIGO_MC = -5;

    Integer CODE_SOAP_EX = -2;
    Integer CODE_ERROR_WS_CLIENT = -3;
    Integer CODE_ERROR_TBITACORA = -4;

    Integer CODE_CARD_DONT_VALID = -6;
    String MESSAGE_CARD_DONT_VALID = "Los datos de la tarjeta estan incompletos.";
}
