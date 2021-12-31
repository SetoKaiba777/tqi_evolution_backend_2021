package com.kaibacorp.testetqi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotEmpty
    @Email
    @Schema(description = "Entre com seu email de login salvo no banco de dados",example = "caio@teste.com")
    private String email;

    @NotEmpty
    @Schema(description = "Entre com sua senha",example = "testando123")
    private String senha;
}
