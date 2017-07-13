package com.example.pipe.ubb;

/**
 * Created by Pipe on 23-06-2017.
 */

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class modificarTest {
   
    @Test
    public void sonIguales() throws Exception {

        assertTrue(modificar.validarClave("usuario","usuario"));
    }

    @Test
    public void TelefonoValido() throws Exception {

        assertTrue(modificar.telefonoValido("328932892"));
    }
    @Test
    public void TelefonoNoValido() throws Exception {

        assertFalse(modificar.telefonoValido("38349"));
    }

    @Test
    public void SoloNumeros() throws Exception {

        assertFalse(modificar.verifyNumeros("3834sc9"));
    }

    @Test
    public void SoloNumerostrue() throws Exception {

        assertTrue(modificar.verifyNumeros("38349"));
    }
    @Test
    public void MaximoNumeros() throws Exception{
        assertTrue(modificar.maximoLetrasValido("",0));
        assertTrue(modificar.maximoLetrasValido("1",1));
        assertTrue(modificar.maximoLetrasValido("cinco",5));
        assertTrue(modificar.maximoLetrasValido("1234567890",10));

    }
    @Test
    public void MenorNumeros() throws Exception{
        assertTrue(modificar.maximoLetrasValido("",1));
        assertTrue(modificar.maximoLetrasValido("123",5));
        assertTrue(modificar.maximoLetrasValido("12345m",10));
    }
    @Test
    public void MayorNumeros() throws Exception{
        assertFalse(modificar.maximoLetrasValido("mayor",0));
        assertFalse(modificar.maximoLetrasValido("mayor",1));
        assertFalse(modificar.maximoLetrasValido("mayorque5",5));
        assertFalse(modificar.maximoLetrasValido("letrasynumerosmayoresque10",10));
    }
}
