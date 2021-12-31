package com.kaibacorp.testetqi.service;

import com.kaibacorp.testetqi.api.dto.InputClienteDTO;
import com.kaibacorp.testetqi.api.dto.InputEmprestimoDTO;
import com.kaibacorp.testetqi.api.dto.ResponseDTO;
import com.kaibacorp.testetqi.builder.InputClienteDTOBuilder;
import com.kaibacorp.testetqi.builder.InputEmprestimoDTOBuilder;
import com.kaibacorp.testetqi.core.mapper.ClienteInputMapper;
import com.kaibacorp.testetqi.core.mapper.EmprestimoInputMapper;
import com.kaibacorp.testetqi.domain.exception.BusinessException;
import com.kaibacorp.testetqi.domain.exception.DontFoundException;
import com.kaibacorp.testetqi.domain.model.Cliente;
import com.kaibacorp.testetqi.domain.model.Emprestimo;
import com.kaibacorp.testetqi.domain.repository.ClienteRepository;
import com.kaibacorp.testetqi.domain.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    private final Long VALID_ID = 1L;

    private final Long INVALID_CLIENT_ID = 2L;

    @Mock
    private ClienteRepository clienteRepository;

    private ClienteInputMapper clienteInputMapper = ClienteInputMapper.INSTANCE;

    private EmprestimoInputMapper emprestimoInputMapper = EmprestimoInputMapper.INSTANCE;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void whenClientListRequested_thenShownThis(){
        //Condição inicial
        InputClienteDTO entrada =  InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente cliente1 = clienteInputMapper.toModel(entrada);
        Cliente cliente2 = clienteInputMapper.toModel(entrada);
        Cliente cliente3 = clienteInputMapper.toModel(entrada);
        List<Cliente> clientes = Arrays.asList(cliente1,cliente2,cliente3);

        //Estabelecendo comportamento dos Mocks
        when(clienteRepository.findAll()).thenReturn(clientes);

        var responseList = clienteService.getAll();
        assertEquals(clientes,responseList);

    }

    @Test
    void whenClienteInformed_thenConfirmationShouldBeCreated(){
        //Condições iniciais
       InputClienteDTO entrada =  InputClienteDTOBuilder.builder().build().toInputClientDTO();
       Cliente expectedSavedCliente = clienteInputMapper.toModel(entrada);
       expectedSavedCliente.setId(VALID_ID);

       //Estabelecendo comportamento dos Mocks
       when(clienteRepository.save(expectedSavedCliente)).thenReturn(expectedSavedCliente);
       mocksBehaviorsToSave(expectedSavedCliente,false,false,false);

       //Teste
       var createClienteDTO = clienteService.add(expectedSavedCliente);
       assertEquals(createClienteDTO,menssagemResposta("O Cliente com id "+ VALID_ID +" foi criado com sucesso!"));

    }

    @Test
    void whenClienteIdInformed_thenClientShouldBeShown(){
        //Condições iniciais
        InputClienteDTO entrada =  InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente expectedCliente = clienteInputMapper.toModel(entrada);
        expectedCliente.setId(VALID_ID);

        //Estabelecendo comportamento dos Mocks
        when(clienteRepository.findById(VALID_ID)).thenReturn(Optional.of(expectedCliente));

        //Teste
        var clienteObtido = clienteService.findByClientId(VALID_ID);
        assertEquals(expectedCliente,clienteObtido);

    }

    @Test
    void whenClienteIdInformed_thenAnErrorShouldBeShown(){
        //Condições iniciais
        InputClienteDTO entrada =  InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente expectedCliente = clienteInputMapper.toModel(entrada);
        expectedCliente.setId(VALID_ID);

        //Estabelecendo comportamento dos Mocks
        when(clienteRepository.findById(VALID_ID)).thenReturn(Optional.of(expectedCliente));

        //Teste
        Throwable exception = assertThrows(DontFoundException.class,
                ()->clienteService.findByClientId(INVALID_CLIENT_ID));
        assertEquals("Cliente não encontrado",exception.getMessage());

    }

    @Test
    void whenClienteInformed_thenCPFErrorShouldBeShown(){
        //Condições iniciais
        InputClienteDTO entrada =  InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente expectedSavedCliente = clienteInputMapper.toModel(entrada);
        expectedSavedCliente.setId(VALID_ID);

        //Estabelecendo comportamento dos Mocks
        mocksBehaviorsToSave(expectedSavedCliente,true,false,false);

        //Teste
        Throwable exception = assertThrows(BusinessException.class,
                ()->clienteService.add(expectedSavedCliente));
        assertEquals("Esse CPF já existe em nossa base de dados",exception.getMessage());
    }

    @Test
    void whenClienteInformed_thenEmailErrorShouldBeShown(){
        //Condições iniciais
        InputClienteDTO entrada =  InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente expectedSavedCliente = clienteInputMapper.toModel(entrada);
        expectedSavedCliente.setId(VALID_ID);

        //Estabelecendo comportamento dos Mocks
        mocksBehaviorsToSave(expectedSavedCliente,false,true,false);

        //Teste
        Throwable exception = assertThrows(BusinessException.class,
                ()->clienteService.add(expectedSavedCliente));
        assertEquals("Esse email já existe em nossa base de dados",exception.getMessage());
    }

    @Test
    void whenClienteInformed_thenRGErrorShouldBeShown(){
        //Condições iniciais
        InputClienteDTO entrada =  InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente expectedSavedCliente = clienteInputMapper.toModel(entrada);
        expectedSavedCliente.setId(VALID_ID);

        //Estabelecendo comportamento dos Mocks
        mocksBehaviorsToSave(expectedSavedCliente,false,false,true);

        //Teste
        Throwable exception = assertThrows(BusinessException.class,
                ()->clienteService.add(expectedSavedCliente));
        assertEquals("Esse RG já existe em nossa base de dados",exception.getMessage());
    }

    @Test
    void whenEmprestimoAndClienteIdInformed_thenConfirmationShouldBeCreated(){
        //Condições iniciais
        InputClienteDTO entradaCliente =  InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente cliente = clienteInputMapper.toModel(entradaCliente);
        cliente.setId(1L);
        cliente.setEmprestimos(new ArrayList<Emprestimo>());
        InputEmprestimoDTO entradaEmprestimo = InputEmprestimoDTOBuilder.builder().build().inputEmprestimoDTO();
        Emprestimo emprestimo =  emprestimoInputMapper.toModel(entradaEmprestimo);
        emprestimo.setId(VALID_ID);

        //Estabelecendo comportamento dos Mocks
        when(clienteRepository.findById(VALID_ID)).thenReturn(Optional.of(cliente));

        //Teste
        var responseDTO = clienteService.addEmprestimo(emprestimo, VALID_ID);
        var exceptedResponse = menssagemResposta("Empréstimo solicitado com sucesso!");
        assertEquals(exceptedResponse,responseDTO);
    }

    @Test
    void whenEmprestimoAndClienteIdInformed_thenValidationDateErrorShouldBeShown(){
        //Condições iniciais
        InputClienteDTO entradaCliente =  InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente cliente = clienteInputMapper.toModel(entradaCliente);
        cliente.setId(1L);
        cliente.setEmprestimos(new ArrayList<Emprestimo>());
        InputEmprestimoDTO entradaEmprestimo = InputEmprestimoDTOBuilder.builder().build().inputEmprestimoDTO();
        entradaEmprestimo.setDataPrimeiraParcela("09-12-2022");
        Emprestimo emprestimo =  emprestimoInputMapper.toModel(entradaEmprestimo);
        emprestimo.setId(1L);

        //Estabelecendo comportamento dos Mocks
        when(clienteRepository.findById(VALID_ID)).thenReturn(Optional.of(cliente));

        //Teste
        Throwable exception = assertThrows(BusinessException.class,
                ()->clienteService.addEmprestimo(emprestimo, 1L));
        assertEquals("A data para ínício do empréstimo é inválida!",exception.getMessage());
    }

    @Test
    void whenEmprestimoAndClienteIdInformed_thenValidationParcelErrorShouldBeShown(){
        //Condições iniciais
        InputClienteDTO entradaCliente =  InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente cliente = clienteInputMapper.toModel(entradaCliente);
        cliente.setId(1L);
        cliente.setEmprestimos(new ArrayList<Emprestimo>());
        InputEmprestimoDTO entradaEmprestimo = InputEmprestimoDTOBuilder.builder().build().inputEmprestimoDTO();
        entradaEmprestimo.setNumParcelas(61);
        Emprestimo emprestimo =  emprestimoInputMapper.toModel(entradaEmprestimo);
        emprestimo.setId(1L);

        //Estabelecendo comportamento dos Mocks
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        //Teste
        Throwable exception = assertThrows(BusinessException.class,
                ()->clienteService.addEmprestimo(emprestimo, VALID_ID));
        assertEquals("Número de parcelas inválido, " +
                "valor deve ser menor ou igual a 60!",exception.getMessage());
    }

    @Test
    void whenEmprestimoIdAndClienteIdInformed_thenEmprestimoShouldBeShown(){
        //Condições iniciais
        InputClienteDTO entradaCliente =  InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente cliente = clienteInputMapper.toModel(entradaCliente);
        cliente.setId(VALID_ID);
        cliente.setEmprestimos(new ArrayList<Emprestimo>());
        InputEmprestimoDTO entradaEmprestimo = InputEmprestimoDTOBuilder.builder().build().inputEmprestimoDTO();
        Emprestimo emprestimo =  emprestimoInputMapper.toModel(entradaEmprestimo);
        emprestimo.setId(VALID_ID);
        cliente.getEmprestimos().add(emprestimo);

        //Estabelecendo comportamento dos Mocks
        when(clienteRepository.getById(VALID_ID)).thenReturn(cliente);

        //Teste
        var responseEmprestimo = clienteService.findEmprestimo(cliente.getId(),emprestimo.getId());
        assertEquals(emprestimo,responseEmprestimo);
        }

    @Test
    void whenEmprestimoIdAndClienteIdInformed_thenAnErrorShouldBeShown(){
        //Condições iniciais
        InputClienteDTO entradaCliente =  InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente cliente = clienteInputMapper.toModel(entradaCliente);
        cliente.setId(1L);
        cliente.setEmprestimos(new ArrayList<Emprestimo>());

        //Estabelecendo comportamento dos Mocks
        when(clienteRepository.getById(VALID_ID)).thenReturn(cliente);

        //Teste
        Throwable exception = assertThrows(BusinessException.class,
                ()->clienteService.findEmprestimo(cliente.getId(), VALID_ID));
        assertEquals("Esse empréstimo não pertence ao cliente",exception.getMessage());
    }

    private void mocksBehaviorsToSave(Cliente cliente, boolean bCpf,boolean bEmail, boolean bRg){
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteRepository.existsByCpf(cliente.getCpf())).thenReturn(bCpf);
        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(bEmail);
        when(clienteRepository.existsByRg(cliente.getRg())).thenReturn(bRg);
    }

    private ResponseDTO menssagemResposta(String msg) {
        return ResponseDTO.builder().
                message(msg).
                build();
    }
}
