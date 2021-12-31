package com.kaibacorp.testetqi.domain.repository;

import com.kaibacorp.testetqi.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    public Optional<Cliente> findByEmailAndSenha(String senha,String email);
    public boolean existsByEmail(String email);
    public boolean existsByCpf(String cpf);
    public boolean existsByRg(String rg);
}
