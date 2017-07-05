package com.example.pipe.ubb;

/**
 * Created by Pipe on 23-06-2017.
 */

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EspecificoTest {

    @Test
    public void ReclamoNulo() throws Exception {

        assertThat(Especifico.VerificarMensaje(""), is(false));
    }

    @Test
    public void ReclamoNoNulo() throws Exception {

        assertThat(Especifico.VerificarMensaje("hola"), is(true));
    }

    @Test
    public void ReclamoLargo() throws Exception {

        assertThat(Especifico.VerificarLargoMensaje("asdasdasdasdasdasdasdasooooooooooddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddasdasdasdsadsadsadasdassadadasdasdasdasdasdasdsaasdsadsadsadsadsadsadasdasdasdsadsadsadsadasdsadasdsadsada" +
                "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddss"), is(false));
    }

    @Test
    public void ReclamoNoLargo() throws Exception {

        assertThat(Especifico.VerificarLargoMensaje("asddsadsadas"), is(true));
    }

}

