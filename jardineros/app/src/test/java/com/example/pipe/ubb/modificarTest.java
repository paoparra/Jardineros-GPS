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
}
