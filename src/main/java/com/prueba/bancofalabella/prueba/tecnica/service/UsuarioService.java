package com.prueba.bancofalabella.prueba.tecnica.service;


import com.prueba.bancofalabella.prueba.tecnica.dto.RegisterRequest;
import com.prueba.bancofalabella.prueba.tecnica.dto.RegisterResponse;
import com.prueba.bancofalabella.prueba.tecnica.exception.ApiException;

public interface UsuarioService {
    RegisterResponse registrarUsuario(RegisterRequest request) throws ApiException;
}