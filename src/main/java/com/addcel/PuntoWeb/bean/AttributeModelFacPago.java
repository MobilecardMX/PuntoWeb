package com.addcel.PuntoWeb.bean;

public enum AttributeModelFacPago {
    MARCA_TARJETA("I0"),
    CODIGO_SUB_COMERCION("I1"),
    NUM_REFERENCIA("I2"),
    MONTO_TRANSACCION("I3"),
    MONEDA_TRANSACCION("I4"),
    TARJETA_CREDITO("I5"),
    CVC2("I6"),
    FECHA_EXPIRACION_TARJETA("I7"),
    FECHA_TRANSACCION("I8"),
    HORA_TRANSACCION("I9"),
    AUTOGENERADOR("I10"),
    DIFERIDO("I11"),
    CUOTAS("I12"),
    CODIGO_COMERCIAL_FACILITADOR("I13"),
    TIPO_PROCESO("I14"),
    DATA_HASH("I15"),
    FIRMA_DIGITAL("I16");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    AttributeModelFacPago(String name) {
        this.name = name;
    }
}
