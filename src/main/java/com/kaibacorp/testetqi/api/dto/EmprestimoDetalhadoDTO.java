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
public class EmprestimoDetalhadoDTO {

    @Schema(description = "Id do empréstimo",example = "1")
    private long id;

    @Schema(description = "Valor contratual",example = "2000.00")
    private Double valor;
    @Schema(description = "Data da primeira parcela", example = "dd-MM-yyyy")
    private String dataPrimeiraParcela;

    @Schema(description = "Número de Parcelas",example = "10")
    private int numParcelas;

    @Schema(description = "Renda do Cliente",example = "10000.00")
    private Double renda;

    @Schema(description = "Email do Cliente",example = "caio@teste.com")
    private String email;
}

