package com.kaibacorp.testetqi.core.mapper;

import com.kaibacorp.testetqi.api.dto.InputClienteDTO;
import com.kaibacorp.testetqi.domain.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ClienteInputMapper {

    ClienteInputMapper INSTANCE = Mappers.getMapper(ClienteInputMapper.class);

    Cliente toModel(InputClienteDTO inputClienteDTO);

    InputClienteDTO toDTO(Cliente cliente);
}
