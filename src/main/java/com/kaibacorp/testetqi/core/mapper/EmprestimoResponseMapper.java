package com.kaibacorp.testetqi.core.mapper;

import com.kaibacorp.testetqi.api.dto.EmprestimoResponseDTO;
import com.kaibacorp.testetqi.domain.model.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmprestimoResponseMapper {

    EmprestimoResponseMapper INSTANCE = Mappers.getMapper(EmprestimoResponseMapper.class);

    EmprestimoResponseDTO toDTO(Emprestimo emprestimo);
}
