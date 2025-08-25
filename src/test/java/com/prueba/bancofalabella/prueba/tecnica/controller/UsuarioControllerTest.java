package com.prueba.bancofalabella.prueba.tecnica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.bancofalabella.prueba.tecnica.dto.PhoneRequest;
import com.prueba.bancofalabella.prueba.tecnica.dto.RegisterRequest;
import com.prueba.bancofalabella.prueba.tecnica.dto.RegisterResponse;
import com.prueba.bancofalabella.prueba.tecnica.exception.ApiException;
import com.prueba.bancofalabella.prueba.tecnica.exception.EmailAlreadyExistsException;
import com.prueba.bancofalabella.prueba.tecnica.exception.GlobalExceptionHandler;
import com.prueba.bancofalabella.prueba.tecnica.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UsuarioControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private com.prueba.bancofalabella.prueba.tecnica.UsuarioController.UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController)
                .setControllerAdvice(new GlobalExceptionHandler()) // si tienes un @ControllerAdvice para manejar excepciones
                .build();
        objectMapper = new ObjectMapper(); // inicializamos manualmente
    }

    @Test
    void registrar_Success() throws Exception, ApiException {
        RegisterRequest request = RegisterRequest.builder()
                .name("Juan")
                .email("juan@mail.com")
                .password("Ab12cd34")
                .phones(List.of(new PhoneRequest("12345678", "1", "56")))
                .build();

        RegisterResponse response = RegisterResponse.builder()
                .id(UUID.randomUUID())
                .name("Juan")
                .email("juan@mail.com")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token(UUID.randomUUID().toString())
                .isActive(true)
                .build();

        when(usuarioService.registrarUsuario(any(RegisterRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/usuarios/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())  // 200 OK para éxito
                .andExpect(jsonPath("$.name").value("Juan"))
                .andExpect(jsonPath("$.email").value("juan@mail.com"));

        verify(usuarioService, times(1)).registrarUsuario(any(RegisterRequest.class));
    }


    @Test
    void registrar_EmailExists() throws Exception, ApiException {
        RegisterRequest request = RegisterRequest.builder()
                .name("Juan")
                .email("correo@existente.com")
                .password("Password12")
                .phones(List.of(new PhoneRequest("1234567", "2", "56")))
                .build();

        when(usuarioService.registrarUsuario(any()))
                .thenThrow(new EmailAlreadyExistsException("El correo ya registrado"));

        mockMvc.perform(post("/api/usuarios/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict()) // 409 Conflict
                .andExpect(jsonPath("$.message").value("El correo ya registrado"))
                .andExpect(jsonPath("$.error").value("Email duplicado"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.path").value("/api/usuarios/registro"));
    }
}
