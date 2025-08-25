package com.prueba.bancofalabella.prueba.tecnica.UsuarioController;

import com.prueba.bancofalabella.prueba.tecnica.dto.RegisterRequest;
import com.prueba.bancofalabella.prueba.tecnica.dto.RegisterResponse;
import com.prueba.bancofalabella.prueba.tecnica.exception.ApiException;
import com.prueba.bancofalabella.prueba.tecnica.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/registro")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "Registrar nuevo usuario",
            description = "Crea un usuario con teléfonos",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de registro del usuario",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = RegisterRequest.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Ejemplo usuario",
                                    value = """
                                        {
                                            "name": "Juan Rodriguez",
                                            "email": "juan@rodriguez.org",
                                            "password": "hunter2",
                                            "phones": [
                                                {
                                                    "number": "1234567",
                                                    "citycode": "1",
                                                    "contrycode": "57"
                                                }
                                            ]
                                        }
                                        """
                            )
                    )
            )
    )
    public ResponseEntity<RegisterResponse> registrar(@Valid @RequestBody RegisterRequest request) throws ApiException {
        // Llama al servicio. Si ocurre un error, será capturado por GlobalExceptionHandler
        RegisterResponse response = usuarioService.registrarUsuario(request);
        System.out.println("El nombre es : "+response.getName());
        return ResponseEntity.ok(response);
    }

}