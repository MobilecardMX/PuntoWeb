/*
package com.addcel.PuntoWeb;

import com.addcel.PuntoWeb.util.PuntoWebUtil;
import com.addcel.utils.AddcelCrypto;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@Log4j
@RunWith(SpringRunner.class)
public class UtilTest {

    @Autowired
    private PuntoWebUtil puntoWebUtil;

    @Test
    public void encrypInfo() {
        log.info("tarjeta: " + AddcelCrypto.encryptTarjeta("5455460920094260"));
        log.info("ccv: " + AddcelCrypto.encryptTarjeta("669"));
        log.info("Fecha Exp: " + AddcelCrypto.encryptTarjeta("04/21"));
    }

    @Test
    public void getReferenciaTest(){
        String referenciaInicial = "605";
        String referenciaFinal = puntoWebUtil.getReferencia(referenciaInicial);
        Assert.assertEquals("000605", referenciaFinal);
    }
}
*/
