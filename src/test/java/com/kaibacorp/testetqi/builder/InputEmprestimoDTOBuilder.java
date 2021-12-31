package com.kaibacorp.testetqi.builder;

import com.kaibacorp.testetqi.api.dto.InputEmprestimoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
public class InputEmprestimoDTOBuilder {

    @NotNull
    @Builder.Default
    private Double valor=2000.00;

    @NotEmpty
    @Builder.Default
    private String dataPrimeiraParcela="20-02-2022";

    @NotNull
    @Builder.Default
    private int numParcelas=30;

    public InputEmprestimoDTO inputEmprestimoDTO(){
        return  new InputEmprestimoDTO(valor,
                dataPrimeiraParcela,
                numParcelas);
    }
}
