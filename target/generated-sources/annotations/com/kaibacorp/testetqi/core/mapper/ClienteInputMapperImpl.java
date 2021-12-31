package com.kaibacorp.testetqi.core.mapper;

import com.kaibacorp.testetqi.api.dto.InputClienteDTO;
import com.kaibacorp.testetqi.api.dto.InputClienteDTO.InputClienteDTOBuilder;
import com.kaibacorp.testetqi.domain.model.Cliente;
import com.kaibacorp.testetqi.domain.model.Cliente.ClienteBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-31T07:59:11-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.13 (Ubuntu)"
)
public class ClienteInputMapperImpl implements ClienteInputMapper {

    @Override
    public Cliente toModel(InputClienteDTO inputClienteDTO) {
        if ( inputClienteDTO == null ) {
            return null;
        }

        ClienteBuilder cliente = Cliente.builder();

        cliente.nome( inputClienteDTO.getNome() );
        cliente.rg( inputClienteDTO.getRg() );
        cliente.cpf( inputClienteDTO.getCpf() );
        cliente.endereco( inputClienteDTO.getEndereco() );
        cliente.email( inputClienteDTO.getEmail() );
        cliente.renda( inputClienteDTO.getRenda() );
        cliente.senha( inputClienteDTO.getSenha() );

        return cliente.build();
    }

    @Override
    public InputClienteDTO toDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        InputClienteDTOBuilder inputClienteDTO = InputClienteDTO.builder();

        inputClienteDTO.nome( cliente.getNome() );
        inputClienteDTO.rg( cliente.getRg() );
        inputClienteDTO.cpf( cliente.getCpf() );
        inputClienteDTO.endereco( cliente.getEndereco() );
        inputClienteDTO.email( cliente.getEmail() );
        inputClienteDTO.renda( cliente.getRenda() );
        inputClienteDTO.senha( cliente.getSenha() );

        return inputClienteDTO.build();
    }
}
