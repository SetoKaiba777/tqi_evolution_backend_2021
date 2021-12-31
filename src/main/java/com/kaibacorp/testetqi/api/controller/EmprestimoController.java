package com.kaibacorp.testetqi.api.controller;


import com.kaibacorp.testetqi.api.dto.EmprestimoDetalhadoDTO;
import com.kaibacorp.testetqi.api.dto.LoginDTO;
import com.kaibacorp.testetqi.api.dto.EmprestimoResponseDTO;
import com.kaibacorp.testetqi.domain.service.Login;
import com.kaibacorp.testetqi.core.mapper.EmprestimoResponseMapper;
import com.kaibacorp.testetqi.domain.service.ClienteService;
import com.kaibacorp.testetqi.core.mapper.EmprestimoDetalhadoMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/emprestimos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmprestimoController {

    private ClienteService clienteService;

    private EmprestimoDetalhadoMapper emprestimoDetalhadoMapper;

    private Login login;

    private final EmprestimoResponseMapper emprestimoResponseMapper = EmprestimoResponseMapper.INSTANCE;

    @GetMapping("/{idCliente}")
    @Operation(summary = "Retorna uma lista com todos os empréstimo para o cliente com id = {id}. " +
            "Método exige credenciais de entrada: email e senha do usuário passadas para uri")
    public List<EmprestimoResponseDTO> todosEmprestimos(@Valid LoginDTO loginDTO, @PathVariable Long idCliente){
        var cliente = login.conectar(idCliente,loginDTO);
        return cliente.
                getEmprestimos().
                stream().
                map(e-> emprestimoResponseMapper.toDTO(e)).
                collect(Collectors.toList());
    }

    @Operation(summary = "Retorna o empréstimo detalhado de id = {idEmprestimo}, pertencente cliente de" +
            " idCliente = {idCliente}. Método exige" +
            " credenciais de entrada: email e senha do usuário passadas para uri")
    @GetMapping("/{idCliente}/{idEmprestimo}")
    public EmprestimoDetalhadoDTO emprestimoDetalhe(@Valid LoginDTO loginDTO, @PathVariable Long idCliente ,@PathVariable Long idEmprestimo){
        var cliente = login.conectar(idCliente,loginDTO);
        var emprestimo = clienteService.findEmprestimo(idCliente,idEmprestimo);
        return emprestimoDetalhadoMapper.toDTO(emprestimo,cliente);
    }

}
