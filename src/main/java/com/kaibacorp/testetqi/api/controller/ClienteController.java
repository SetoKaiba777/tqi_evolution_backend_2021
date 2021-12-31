package com.kaibacorp.testetqi.api.controller;


import com.kaibacorp.testetqi.api.dto.ClienteResponseDTO;
import com.kaibacorp.testetqi.api.dto.InputClienteDTO;
import com.kaibacorp.testetqi.api.dto.InputEmprestimoDTO;
import com.kaibacorp.testetqi.api.dto.ResponseDTO;
import com.kaibacorp.testetqi.core.mapper.ClienteInputMapper;
import com.kaibacorp.testetqi.core.mapper.ClienteResponseMapper;
import com.kaibacorp.testetqi.core.mapper.EmprestimoInputMapper;
import com.kaibacorp.testetqi.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clientes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController {

    private ClienteService clienteService;


    private final ClienteInputMapper clienteInputMapper = ClienteInputMapper.INSTANCE;

    private final EmprestimoInputMapper emprestimoInputMapper = EmprestimoInputMapper.INSTANCE;

    private final ClienteResponseMapper clienteResponseMapper = ClienteResponseMapper.INSTANCE;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo cliente")
    public ResponseDTO novoCliente(@Valid @RequestBody InputClienteDTO inputClienteDTO){
        var novoCliente = clienteInputMapper.toModel(inputClienteDTO);
        return clienteService.add(novoCliente);
    }

    @GetMapping
    @Operation(summary = "Permite a visualização de todos os clientes")
    public List<ClienteResponseDTO> todosClientes(){
        return clienteService.getAll().stream().
                map(cliente -> clienteResponseMapper.toDTO(cliente)).
                collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Permite a visualização do cliente com id={id}")
    public ClienteResponseDTO clienteEspecifico(@PathVariable Long id) {
        return clienteResponseMapper.
                toDTO(clienteService.findByClientId(id));
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo empréstimo para o cliente com id = {id}")
    public ResponseDTO novoEmprestimo(@Valid @RequestBody InputEmprestimoDTO emprestimoDTO, @PathVariable Long id){
        var novoEmprestimo = emprestimoInputMapper.toModel(emprestimoDTO);
        return clienteService.addEmprestimo(novoEmprestimo,id);
    }

}
