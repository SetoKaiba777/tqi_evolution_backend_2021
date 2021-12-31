package com.kaibacorp.testetqi.core.mapper;

import com.kaibacorp.testetqi.api.dto.InputClienteDTO;
import com.kaibacorp.testetqi.api.dto.InputEmprestimoDTO;
import com.kaibacorp.testetqi.domain.model.Cliente;
import com.kaibacorp.testetqi.domain.model.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmprestimoInputMapper {

    EmprestimoInputMapper INSTANCE = Mappers.getMapper(EmprestimoInputMapper.class);

    @Mapping(target = "dataPrimeiraParcela",source = "dataPrimeiraParcela", dateFormat = "dd-MM-yyy")
    Emprestimo toModel(InputEmprestimoDTO inputEmprestimoDTO);

    InputClienteDTO toDTO(Cliente cliente);
}
