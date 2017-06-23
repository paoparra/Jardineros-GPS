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
}
