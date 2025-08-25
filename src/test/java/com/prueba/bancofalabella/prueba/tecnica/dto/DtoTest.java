package com.prueba.bancofalabella.prueba.tecnica.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DtoTest {

    @Test
    void phoneRequest_BuilderAndGetters() {
        PhoneRequest phone = PhoneRequest.builder()
                .number("123456")
                .citycode("1")
                .contrycode("56")
                .build();

        assertEquals("123456", phone.getNumber());
        assertEquals("1", phone.getCitycode());
        assertEquals("56", phone.getContrycode());
    }

    @Test
    void registerRequest_BuilderAndGetters() {
        RegisterRequest request = RegisterRequest.builder()
                .name("Juan")
                .email("juan@mail.com")
                .password("Ab12cd34")
                .phones(List.of(new PhoneRequest("12345678", "1", "56")))
                .build();

        assertEquals("Juan", request.getName());
        assertEquals("juan@mail.com", request.getEmail());
        assertEquals("Ab12cd34", request.getPassword());
        assertEquals(1, request.getPhones().size());
    }

    @Test
    void registerResponse_BuilderAndGetters() {
        RegisterResponse response = RegisterResponse.builder()
                .name("Juan")
                .email("juan@mail.com")
                .isActive(true)
                .build();

        assertEquals("Juan", response.getName());
        assertEquals("juan@mail.com", response.getEmail());
        assertTrue(response.isActive());
    }
}