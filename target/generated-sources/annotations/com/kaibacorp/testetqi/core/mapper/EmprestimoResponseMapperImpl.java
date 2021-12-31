package com.kaibacorp.testetqi.core.mapper;

import com.kaibacorp.testetqi.api.dto.EmprestimoResponseDTO;
import com.kaibacorp.testetqi.api.dto.EmprestimoResponseDTO.EmprestimoResponseDTOBuilder;
import com.kaibacorp.testetqi.domain.model.Emprestimo;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-31T07:59:11-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.13 (Ubuntu)"
)
public class EmprestimoResponseMapperImpl implements EmprestimoResponseMapper {

    @Override
    public EmprestimoResponseDTO toDTO(Emprestimo emprestimo) {
        if ( emprestimo == null ) {
            return null;
        }

        EmprestimoResponseDTOBuilder emprestimoResponseDTO = EmprestimoResponseDTO.builder();

        emprestimoResponseDTO.id( emprestimo.getId() );
        emprestimoResponseDTO.valor( emprestimo.getValor() );
        emprestimoResponseDTO.numParcelas( emprestimo.getNumParcelas() );

        return emprestimoResponseDTO.build();
    }
}
