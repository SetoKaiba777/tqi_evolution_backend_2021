package com.kaibacorp.testetqi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {

    @Schema(description ="Id do cliente",example = "1")
    private  long id;

    @Schema(description ="Nome do cliente",example = "Caio Oliveira")
    private String nome;

    @Schema(description = "Número de identidade", example = "SP-99.999.99")
    private String rg;

    @Schema(description = "CPF do cliente",example = "091.946.376-28")
    private String cpf;

    @Schema(description = "Endereço do cliente",example = "Rua das couves,999")
    private String endereco;


    @Schema(description = "Email do cliente",example = "caio@teste.com")
    private String email;


    @Schema(description = "Renda do cliente",example = "7.00")
    private Double renda;

    @Schema(description = "Emprestimos do cliente")
    private List<EmprestimoResponseDTO> emprestimos;


}
