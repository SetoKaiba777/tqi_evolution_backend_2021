package com.kaibacorp.testetqi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InputClienteDTO {

    @NotEmpty
    @Size(min=2, max=100)
    @Schema(description = "Entre com o nome",example = "Caio Oliveira")
    private String nome;

    @NotEmpty
    @Schema(description = "Digite o número de identidade", example = "SP-99.999.99")
    private String rg;

    @CPF
    @NotEmpty
    @Schema(description = "Entre com um CPF válido",example = "091.946.376-28")
    private String cpf;

    @NotEmpty
    @Schema(description = "Entre com seu endereço",example = "Rua das couves,999")
    private String endereco;

    @Email
    @NotEmpty
    @Schema(description = "Entre com um email válido",example = "caio@teste.com")
    private String email;

    @NotNull
    @Schema(description = "Entre com a renda",example = "7.00")
    private Double renda;

    @NotEmpty
    @Size(min=6, max=100)
    @Schema(description = "Crie uma senha",example = "testando123")
    private String senha;

}
