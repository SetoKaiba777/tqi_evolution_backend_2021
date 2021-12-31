package com.kaibacorp.testetqi.builder;

import com.kaibacorp.testetqi.api.dto.InputClienteDTO;
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

@Builder
public class InputClienteDTOBuilder {

    @NotEmpty
    @Size(min=2, max=100)
    @Builder.Default
    private String nome="Caio";

    @NotEmpty
    @Builder.Default
    private String rg = "MG-99.999.999";

    @CPF
    @NotEmpty
    @Builder.Default
    private String cpf = "091.946.376-28";

    @NotEmpty
    @Builder.Default
    private String endereco="Rua das couves,999";

    @Email
    @NotEmpty
    @Builder.Default
    private String email = "caio@teste.com";

    @NotNull
    @Builder.Default
    private Double renda = 20.00;

    @NotEmpty
    @Size(min=6, max=100)
    @Builder.Default
    private String senha="senha123";

    public InputClienteDTO toInputClientDTO(){
        return new InputClienteDTO(nome,
                rg,
                cpf,
                endereco,
                email,
                renda,
                senha);
    }

}
