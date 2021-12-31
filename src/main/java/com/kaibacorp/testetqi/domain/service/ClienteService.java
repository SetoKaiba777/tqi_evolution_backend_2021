package com.kaibacorp.testetqi.domain.service;

import com.kaibacorp.testetqi.api.dto.ResponseDTO;
import com.kaibacorp.testetqi.domain.enums.ConstantesValidacao;
import com.kaibacorp.testetqi.domain.exception.DontFoundException;
import com.kaibacorp.testetqi.domain.exception.BusinessException;
import com.kaibacorp.testetqi.domain.model.Cliente;
import com.kaibacorp.testetqi.domain.model.Emprestimo;
import com.kaibacorp.testetqi.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteService {

    private ClienteRepository clienteRepository;

    private static final ConstantesValidacao diasMax = ConstantesValidacao.DIAS_MAX;
    private static final ConstantesValidacao parcelasMax = ConstantesValidacao.PARCELAS_MAX;

    public ResponseDTO add(Cliente cliente){
        fieldsExisits(cliente);
        var novoCliente = clienteRepository.save(cliente);
        return menssagemResposta("O Cliente com id "+novoCliente.getId()+" foi criado com sucesso!");
    }

    public Emprestimo findEmprestimo(Long idCliente,Long idEmprestimo){
        var emp = clienteRepository.
                getById(idCliente).
                getEmprestimos().
                stream().
                filter(emprestimo -> emprestimo.getId()== idEmprestimo).collect(Collectors.toList());
        if(emp.size()==0) throw new BusinessException("Esse empréstimo não pertence ao cliente");
        return emp.get(0);
    }

    public ResponseDTO addEmprestimo(Emprestimo emprestimo, Long id){
        var cliente = findByClientId(id);
        var dias = LocalDate.now().
                until(emprestimo.getDataPrimeiraParcela(), ChronoUnit.DAYS);
        if(dias > diasMax.getValues()) throw new BusinessException("A data para ínício do empréstimo é inválida!");
        if(emprestimo.getNumParcelas()>parcelasMax.getValues())throw new BusinessException("Número de parcelas inválido, " +
                "valor deve ser menor ou igual a 60!");
        cliente.getEmprestimos().add(emprestimo);
        return menssagemResposta("Empréstimo solicitado com sucesso!");
    }

    public List<Cliente> getAll(){
        return clienteRepository.findAll();
    }

    public Cliente findByClientId(Long id){
        return clienteRepository.
                findById(id).
                orElseThrow(()-> new DontFoundException("Cliente não encontrado"));
    }

    public void delete(Long id){
        clienteRepository.deleteById(id);
    }

    private ResponseDTO menssagemResposta(String msg) {
        return ResponseDTO.builder().
                message(msg).
                build();
    }

    private void fieldsExisits(Cliente cliente){
        if (clienteRepository.existsByEmail(cliente.getEmail())){
            throw new BusinessException("Esse email já existe em nossa base de dados");
        }
        if (clienteRepository.existsByCpf(cliente.getCpf())){
            throw new BusinessException("Esse CPF já existe em nossa base de dados");
        }
        if (clienteRepository.existsByRg(cliente.getRg())){
            throw new BusinessException("Esse RG já existe em nossa base de dados");
        }
    }
}
