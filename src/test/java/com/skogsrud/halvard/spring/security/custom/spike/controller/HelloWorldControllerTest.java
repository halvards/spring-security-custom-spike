package com.skogsrud.halvard.spring.security.custom.spike.controller;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class HelloWorldControllerTest {
    @Test
    public void testHelloWorld() throws Exception {
        assertThat(new HelloWorldController().hello(), equalTo("Hello world"));
    }
}
