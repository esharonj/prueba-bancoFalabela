package com.prueba.bancofalabella.prueba.tecnica.service;

import com.prueba.bancofalabella.prueba.tecnica.dto.RegisterRequest;
import com.prueba.bancofalabella.prueba.tecnica.dto.RegisterResponse;
import com.prueba.bancofalabella.prueba.tecnica.entity.User;
import com.prueba.bancofalabella.prueba.tecnica.exception.ApiException;
import com.prueba.bancofalabella.prueba.tecnica.exception.EmailAlreadyExistsException;
import com.prueba.bancofalabella.prueba.tecnica.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Primary
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse registrarUsuario(RegisterRequest request) throws ApiException {
        // Validar email duplicado
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("El correo ya está registrado");
        }

        // Instante actual
        LocalDateTime now = LocalDateTime.now();

        // Mapear entidad
        User usuario = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .created(now)
                .modified(now)
                .lastLogin(now)
                .token(UUID.randomUUID().toString())
                .isActive(true)
                .build();

        usuarioRepository.save(usuario);

        // Respuesta
        return RegisterResponse.builder()
                .id(usuario.getId())
                .name(usuario.getName())
                .email(usuario.getEmail())
                .created(usuario.getCreated())
                .modified(usuario.getModified())
                .lastLogin(usuario.getLastLogin())
                .token(usuario.getToken())
                .isActive(usuario.isActive())
                .build();
    }

}
