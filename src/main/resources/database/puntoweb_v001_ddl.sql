-- crear tabla de configuracion
CREATE TABLE PUNTO_WEB_CONFIG_PARAM(
    ID_CONFIG_PARAM INT AUTO_INCREMENT,
    KEY_PARAM VARCHAR(50),
    VALUE_PARAM VARCHAR(255),

    PRIMARY KEY(ID_CONFIG_PARAM)
);

-- crear tabla de bitacora
CREATE TABLE PUNTO_WEB_BITACORA(
    ID_BITACORA	int(11) AUTO_INCREMENT,
    FECHA	date,
    REQUEST_ENDPOINT	varchar(255),
    RESPONSE_ENDPOINT	varchar(255),
    REQUEST_PUNTOWEB	varchar(255),
    RESPONSE_PUNTOWEB	text,
    DESCRIPCION	varchar(255),
    ID_APP	int(11),
    ID_PAIS	varchar(10),
    IDIOMA	varchar(20),

    PRIMARY KEY (ID_BITACORA)
);