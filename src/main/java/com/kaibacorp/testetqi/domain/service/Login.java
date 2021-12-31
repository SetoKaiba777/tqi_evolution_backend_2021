package com.kaibacorp.testetqi.domain.service;

import com.kaibacorp.testetqi.api.dto.LoginDTO;
import com.kaibacorp.testetqi.domain.exception.LoginException;
import com.kaibacorp.testetqi.domain.model.Cliente;
import com.kaibacorp.testetqi.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Login {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente conectar(Long id, LoginDTO loginDTO){
        var cliente= clienteRepository.findByEmailAndSenha(loginDTO.getEmail(),loginDTO.getSenha()).
               orElseThrow(()-> new LoginException("Senha ou Login estão incorretos, tente novamente"));
        if(!cliente.getId().equals(id)){
            throw new LoginException("Senha ou Login estão incorretos, tente novamente");
        }
        return cliente;
    }
}
