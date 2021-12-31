package com.kaibacorp.testetqi.domain.repository;

import com.kaibacorp.testetqi.domain.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {
}
