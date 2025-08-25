package com.prueba.bancofalabella.prueba.tecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class PhoneRequest {

    @Schema(description = "Número de teléfono", example = "1234567")
    private String number;

    @Schema(description = "Código de ciudad", example = "1")
    private String citycode;

    @Schema(description = "Código de país", example = "57")
    private String contrycode;

}
