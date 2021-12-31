package com.kaibacorp.testetqi.builder;

import com.kaibacorp.testetqi.api.dto.LoginDTO;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
public class LoginDTOBuilder {
    @NotEmpty
    @Email
    @Builder.Default
    private String email="caio@teste.com";

    @NotEmpty
    @Builder.Default
    private String senha="senha123";

    public LoginDTO loginDTOBuilder(){
        return new LoginDTO(email,
                senha);
    }
}
