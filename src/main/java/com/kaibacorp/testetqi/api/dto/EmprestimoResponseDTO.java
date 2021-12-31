package com.kaibacorp.testetqi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoResponseDTO {

    @Schema(description = "Id do empréstimo",example = "1")
    private long id;

    @Schema(description = "Valor contratual",example = "2000.00")
    private Double valor;

    @Schema(description = "Número de Parcelas",example = "12")
    private int numParcelas;
}
