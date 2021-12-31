package com.kaibacorp.testetqi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
    @Schema(description = "Mensagem de resposta da DTO",example = "O Cliente com id 1 foi criado com sucesso!")
    private String message;
}
