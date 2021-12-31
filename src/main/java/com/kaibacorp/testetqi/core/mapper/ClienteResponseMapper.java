package com.kaibacorp.testetqi.core.mapper;

import com.kaibacorp.testetqi.api.dto.ClienteResponseDTO;
import com.kaibacorp.testetqi.domain.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ClienteResponseMapper {

    ClienteResponseMapper INSTANCE = Mappers.getMapper(ClienteResponseMapper.class);

    Cliente toModel(ClienteResponseDTO clienteResponseDTO);

    ClienteResponseDTO toDTO(Cliente cliente);
}
