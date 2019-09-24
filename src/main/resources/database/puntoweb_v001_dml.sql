-- crear usuario de seguridad para el servicio de Punto WEB
INSERT INTO APP_AUTHORIZATION (id_application, username, password, activo) VALUES ('Punto Web', 'puntoweb', 'e9d840a2746e11e98f9e2a86e4085a59', 'T');

-- configuracion del API
INSERT INTO PUNTO_WEB_CONFIG_PARAM (KEY_PARAM, VALUE_PARAM) VALUES('COMERCIO_REQUEST', '4059821');
INSERT INTO PUNTO_WEB_CONFIG_PARAM (KEY_PARAM, VALUE_PARAM) VALUES('MARCA_REQUEST', 'MC');
INSERT INTO PUNTO_WEB_CONFIG_PARAM (KEY_PARAM, VALUE_PARAM) VALUES('COMERCIO_FACILITADOR_REQUEST', '4059821');
-- dev key n9yA9r4SedrUmuSeh4wRejEpAc7aHeCE
-- INSERT INTO PUNTO_WEB_CONFIG_PARAM (KEY_PARAM, VALUE_PARAM) VALUES('KEY_MERCHANT_SOLES', 'n9yA9r4SedrUmuSeh4wRejEpAc7aHeCE');
INSERT INTO PUNTO_WEB_CONFIG_PARAM (KEY_PARAM, VALUE_PARAM) VALUES('KEY_MERCHANT_SOLES', '8ar3pr5Drlzupr0kitHU3IDRIplWlpHI');


-- tarjeta de prueba de punto web
INSERT INTO mobilecard.tarjetas_usuario
(id_aplicacion, idusuario, numerotarjeta, vigencia, estado, idbanco, idfranquicia, idtarjetas_tipo, fecharegistro, ct, nombre_tarjeta, usrDomAmex, usrCpAmex, mobilecard, previvale, `3d_secure`, id_profile_viamericas, act_type, clabe, num_cuenta, address, card_token, masked_pan)
VALUES(1, 8923233103949, 'bEcPHw5cpDJomcDDu9vNeQ==', 'Qau3ejxK7Blqb0uqLnNWHA==', 0, 1, 1, 1, SYSDATE, 'iLI6sbfY0CUQGOn4BoZT3A==', 'CARLOS GARCIA', '', '', 0, NULL, NULL, '', '', '', '', NULL, NULL, NULL);

