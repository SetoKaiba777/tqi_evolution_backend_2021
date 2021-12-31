package com.kaibacorp.testetqi.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id e PK do modelo Cliente",example = "1")
    private Long id;

    @Column(name = "Nome",nullable = false)
    @Schema(description = "Nome do cliente",example = "Caio")
    private String nome;


    @Column(name = "RG", unique = true,nullable = false)
    @Schema(description = "RG do cliente",example = "SP-99.999.999")
    private String rg;

    @Column(name = "CPF", unique = true,nullable = false)
    @Schema(description = "CPF do cliente",example = "999.999.999-99")
    private String cpf;

    @Column(name = "Endereco",nullable = false)
    @Schema(description = "Endereço do cliente",example = "Rua das couves, 999")
    private String endereco;

    @Column(name = "Email",unique = true, nullable = false)
    @Schema(description = "Email do cliente",example = "caio@teste.com.br")
    private String email;

    @Column(name = "Renda", nullable = false)
    @Schema(description = "Renda do cliente",example = "1500.00")
    private Double renda;

    @Schema(description = "Senha do cliente",example = "testando123")
    private String senha;

    @Schema(description = "Lista de empréstimos do cliente")
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
    private List<Emprestimo> emprestimos;
}
