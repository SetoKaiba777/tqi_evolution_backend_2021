package com.kaibacorp.testetqi.core.mapper;

import com.kaibacorp.testetqi.api.dto.ClienteResponseDTO;
import com.kaibacorp.testetqi.api.dto.ClienteResponseDTO.ClienteResponseDTOBuilder;
import com.kaibacorp.testetqi.api.dto.EmprestimoResponseDTO;
import com.kaibacorp.testetqi.api.dto.EmprestimoResponseDTO.EmprestimoResponseDTOBuilder;
import com.kaibacorp.testetqi.domain.model.Cliente;
import com.kaibacorp.testetqi.domain.model.Cliente.ClienteBuilder;
import com.kaibacorp.testetqi.domain.model.Emprestimo;
import com.kaibacorp.testetqi.domain.model.Emprestimo.EmprestimoBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-31T07:59:11-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.13 (Ubuntu)"
)
public class ClienteResponseMapperImpl implements ClienteResponseMapper {

    @Override
    public Cliente toModel(ClienteResponseDTO clienteResponseDTO) {
        if ( clienteResponseDTO == null ) {
            return null;
        }

        ClienteBuilder cliente = Cliente.builder();

        cliente.id( clienteResponseDTO.getId() );
        cliente.nome( clienteResponseDTO.getNome() );
        cliente.rg( clienteResponseDTO.getRg() );
        cliente.cpf( clienteResponseDTO.getCpf() );
        cliente.endereco( clienteResponseDTO.getEndereco() );
        cliente.email( clienteResponseDTO.getEmail() );
        cliente.renda( clienteResponseDTO.getRenda() );
        cliente.emprestimos( emprestimoResponseDTOListToEmprestimoList( clienteResponseDTO.getEmprestimos() ) );

        return cliente.build();
    }

    @Override
    public ClienteResponseDTO toDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteResponseDTOBuilder clienteResponseDTO = ClienteResponseDTO.builder();

        if ( cliente.getId() != null ) {
            clienteResponseDTO.id( cliente.getId() );
        }
        clienteResponseDTO.nome( cliente.getNome() );
        clienteResponseDTO.rg( cliente.getRg() );
        clienteResponseDTO.cpf( cliente.getCpf() );
        clienteResponseDTO.endereco( cliente.getEndereco() );
        clienteResponseDTO.email( cliente.getEmail() );
        clienteResponseDTO.renda( cliente.getRenda() );
        clienteResponseDTO.emprestimos( emprestimoListToEmprestimoResponseDTOList( cliente.getEmprestimos() ) );

        return clienteResponseDTO.build();
    }

    protected Emprestimo emprestimoResponseDTOToEmprestimo(EmprestimoResponseDTO emprestimoResponseDTO) {
        if ( emprestimoResponseDTO == null ) {
            return null;
        }

        EmprestimoBuilder emprestimo = Emprestimo.builder();

        emprestimo.id( emprestimoResponseDTO.getId() );
        emprestimo.valor( emprestimoResponseDTO.getValor() );
        emprestimo.numParcelas( emprestimoResponseDTO.getNumParcelas() );

        return emprestimo.build();
    }

    protected List<Emprestimo> emprestimoResponseDTOListToEmprestimoList(List<EmprestimoResponseDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Emprestimo> list1 = new ArrayList<Emprestimo>( list.size() );
        for ( EmprestimoResponseDTO emprestimoResponseDTO : list ) {
            list1.add( emprestimoResponseDTOToEmprestimo( emprestimoResponseDTO ) );
        }

        return list1;
    }

    protected EmprestimoResponseDTO emprestimoToEmprestimoResponseDTO(Emprestimo emprestimo) {
        if ( emprestimo == null ) {
            return null;
        }

        EmprestimoResponseDTOBuilder emprestimoResponseDTO = EmprestimoResponseDTO.builder();

        emprestimoResponseDTO.id( emprestimo.getId() );
        emprestimoResponseDTO.valor( emprestimo.getValor() );
        emprestimoResponseDTO.numParcelas( emprestimo.getNumParcelas() );

        return emprestimoResponseDTO.build();
    }

    protected List<EmprestimoResponseDTO> emprestimoListToEmprestimoResponseDTOList(List<Emprestimo> list) {
        if ( list == null ) {
            return null;
        }

        List<EmprestimoResponseDTO> list1 = new ArrayList<EmprestimoResponseDTO>( list.size() );
        for ( Emprestimo emprestimo : list ) {
            list1.add( emprestimoToEmprestimoResponseDTO( emprestimo ) );
        }

        return list1;
    }
}
