package com.example.pipe.ubb;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Paola Alejandra on 22-06-2017.
 */
public class RegistrarUsuarioJardineroTest {
    @Test
    public void isEmailValidTest1() throws Exception {
        assertThat(RegistrarUsuarioJardinero.isEmailValid("correo@correo.cl"),is(true));
    }

    @Test
    public void isEmailValidTest2() throws Exception{
        assertThat(RegistrarUsuarioJardinero.isEmailValid("correo@correo"),is(false));
    }

    @Test
    public void isEmailValidTest3() throws Exception{
        assertThat(RegistrarUsuarioJardinero.isEmailValid(" "),is(false));
    }

    @Test
    public void isEmailValidTest4() throws Exception{
        assertThat(RegistrarUsuarioJardinero.isEmailValid("prueba"),is(false));
    }

    @Test
    public void isEmailValidTest5() throws Exception{
        assertThat(RegistrarUsuarioJardinero.isEmailValid("prueba@ .cl"),is(false));
    }

    @Test
    public void isEmailValidTest6() throws Exception{
        assertThat(RegistrarUsuarioJardinero.isEmailValid(" @ . "),is(false));
    }

    @Test
    public void isEmailValidTest7() throws Exception{
        assertThat(RegistrarUsuarioJardinero.isEmailValid("1@1.1"),is(false));
    }

    @Test
    public void isEmailValidTest8() throws Exception{
        assertThat(RegistrarUsuarioJardinero.isEmailValid("correo1@correo.cl"),is(true));
    }

}