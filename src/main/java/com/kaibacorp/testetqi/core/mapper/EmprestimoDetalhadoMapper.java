package com.kaibacorp.testetqi.core.mapper;

import com.kaibacorp.testetqi.api.dto.EmprestimoDetalhadoDTO;
import com.kaibacorp.testetqi.domain.model.Cliente;
import com.kaibacorp.testetqi.domain.model.Emprestimo;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class EmprestimoDetalhadoMapper {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");

    public EmprestimoDetalhadoDTO toDTO(Emprestimo emprestimo, Cliente cliente){
        EmprestimoDetalhadoDTO emprestimoDetalhado = new EmprestimoDetalhadoDTO();
        emprestimoDetalhado.setId(emprestimo.getId());
        emprestimoDetalhado.setNumParcelas(emprestimo.getNumParcelas());
        emprestimoDetalhado.setValor(emprestimo.getValor());
        emprestimoDetalhado.setDataPrimeiraParcela(formatter.format(emprestimo.getDataPrimeiraParcela()));
        emprestimoDetalhado.setRenda(cliente.getRenda());
        emprestimoDetalhado.setEmail(cliente.getEmail());
        return emprestimoDetalhado;
    }
}
