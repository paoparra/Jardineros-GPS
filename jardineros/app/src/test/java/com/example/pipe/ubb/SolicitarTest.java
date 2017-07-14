package com.example.pipe.ubb;

/**
 * Created by Pipe on 23-06-2017.
 */

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SolicitarTest {

    @Test
    public void HoraMala() throws Exception {

        assertThat(Solicitar.HoraValida(24,25), is(false));
    }

    @Test
    public void HoraBuena() throws Exception {

        assertThat(Solicitar.HoraValida(17,25), is(true));
    }

    @Test
    public void MinutoMalo() throws Exception {

        assertThat(Solicitar.HoraValida(18,84), is(false));
    }

    @Test
    public void MinutoBueno() throws Exception {

        assertThat(Solicitar.HoraValida(18,41), is(true));
    }


}