package com.prueba.bancofalabella.prueba.tecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotEmpty
    @Schema(description = "Nombre completo del usuario", example = "Juan Rodriguez")
    private String name;

    @NotEmpty
    @Email
    @Schema(description = "Correo electrónico del usuario", example = "juan@rodriguez.org")
    private String email;

    @NotEmpty
    @Schema(description = "Contraseña del usuario", example = "hunter2")
    private String password;

    @Schema(description = "Lista de teléfonos del usuario")
    private List<PhoneRequest> phones;

}
