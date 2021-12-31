package com.kaibacorp.testetqi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InputEmprestimoDTO {

    @NotNull
    @Schema(description = "Entre com o valor do empréstimo", example = "2000.00")
    private Double valor;

    @NotEmpty
    @Schema(description = "Entre com a data da primeira pracela, formato dd-mm-YYYY", example = "25-01-2022")
    private String dataPrimeiraParcela;

    @NotNull
    @Schema(description = "Entre com o número de parcelas", example = "60")
    private int numParcelas;

}
