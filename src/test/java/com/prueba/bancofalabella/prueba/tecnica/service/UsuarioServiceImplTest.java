package com.prueba.bancofalabella.prueba.tecnica.service;

import com.prueba.bancofalabella.prueba.tecnica.dto.PhoneRequest;
import com.prueba.bancofalabella.prueba.tecnica.dto.RegisterRequest;
import com.prueba.bancofalabella.prueba.tecnica.dto.RegisterResponse;
import com.prueba.bancofalabella.prueba.tecnica.entity.User;
import com.prueba.bancofalabella.prueba.tecnica.exception.ApiException;
import com.prueba.bancofalabella.prueba.tecnica.exception.EmailAlreadyExistsException;
import com.prueba.bancofalabella.prueba.tecnica.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarUsuario_Success() throws ApiException {
        RegisterRequest request = RegisterRequest.builder()
                .name("Juan")
                .email("juan@mail.com")
                .password("Ab12cd34")
                .phones(List.of(new PhoneRequest("12345678", "1", "56")))
                .build();

        when(usuarioRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");

        // Mockear el save para devolver un User con ID generado
        when(usuarioRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(UUID.randomUUID()); // simula la generación de ID por la base de datos
            return u;
        });

        RegisterResponse response = usuarioService.registrarUsuario(request);

        assertNotNull(response.getId());
        assertEquals("Juan", response.getName());
        assertEquals("juan@mail.com", response.getEmail());
        assertTrue(response.isActive());

        verify(usuarioRepository, times(1)).save(any(User.class));
    }


    @Test
    void registrarUsuario_EmailAlreadyExists() {
        // Arrange
        RegisterRequest request = RegisterRequest.builder()
                .name("Juan")
                .email("correo@existente.com")
                .password("Password12")
                .phones(List.of(new PhoneRequest("1234567", "2", "56")))
                .build();

        when(usuarioRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(new User()));

        // Act & Assert
        assertThrows(EmailAlreadyExistsException.class, () -> {
            usuarioService.registrarUsuario(request);
        });
    }

}
