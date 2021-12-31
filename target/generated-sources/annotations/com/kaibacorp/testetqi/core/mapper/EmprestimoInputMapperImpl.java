package com.kaibacorp.testetqi.core.mapper;

import com.kaibacorp.testetqi.api.dto.InputClienteDTO;
import com.kaibacorp.testetqi.api.dto.InputClienteDTO.InputClienteDTOBuilder;
import com.kaibacorp.testetqi.api.dto.InputEmprestimoDTO;
import com.kaibacorp.testetqi.domain.model.Cliente;
import com.kaibacorp.testetqi.domain.model.Emprestimo;
import com.kaibacorp.testetqi.domain.model.Emprestimo.EmprestimoBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-31T07:59:11-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.13 (Ubuntu)"
)
public class EmprestimoInputMapperImpl implements EmprestimoInputMapper {

    @Override
    public Emprestimo toModel(InputEmprestimoDTO inputEmprestimoDTO) {
        if ( inputEmprestimoDTO == null ) {
            return null;
        }

        EmprestimoBuilder emprestimo = Emprestimo.builder();

        if ( inputEmprestimoDTO.getDataPrimeiraParcela() != null ) {
            emprestimo.dataPrimeiraParcela( LocalDate.parse( inputEmprestimoDTO.getDataPrimeiraParcela(), DateTimeFormatter.ofPattern( "dd-MM-yyy" ) ) );
        }
        emprestimo.valor( inputEmprestimoDTO.getValor() );
        emprestimo.numParcelas( inputEmprestimoDTO.getNumParcelas() );

        return emprestimo.build();
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
