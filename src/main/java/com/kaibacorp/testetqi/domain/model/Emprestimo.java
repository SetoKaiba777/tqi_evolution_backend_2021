package com.kaibacorp.testetqi.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id e PK do modelo Empréstimo",example = "1")
    private long id;

    @Column(name = "Valor",nullable = false)
    @Schema(description = "Valor contratual do empréstimo",example = "12000.00")
    private Double valor;

    @Column(name = "Data_Primeira_Parcela",nullable = false)
    @Schema(description = "Data da primeira parcela",example = "1")
    private LocalDate dataPrimeiraParcela;

    @Column(name = "Num_Parcela",nullable = false)
    @Schema(description = "Números de parcelas",example = "1")
    private int numParcelas;


}
