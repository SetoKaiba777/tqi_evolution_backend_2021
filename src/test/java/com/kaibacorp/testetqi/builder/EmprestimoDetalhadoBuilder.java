package com.kaibacorp.testetqi.builder;

import com.kaibacorp.testetqi.api.dto.EmprestimoDetalhadoDTO;
import com.kaibacorp.testetqi.core.mapper.EmprestimoDetalhadoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public class EmprestimoDetalhadoBuilder {

    @Builder.Default
    private long id =1L;

    @Builder.Default
    private Double valor = 2000.00;

    @Builder.Default
    private String dataPrimeiraParcela="20-02-2022";

    @Builder.Default
    private int numParcelas=30;

    @Builder.Default
    private Double renda=20.00;

    @Builder.Default
    private String email="caio@teste.com";

    public EmprestimoDetalhadoDTO emprestimoDetalhadoDTObuilder(){
        return new EmprestimoDetalhadoDTO(id,valor,dataPrimeiraParcela,numParcelas,renda,email);
    }
}
