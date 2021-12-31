package com.kaibacorp.testetqi.controller;

import com.kaibacorp.testetqi.api.controller.EmprestimoController;
import com.kaibacorp.testetqi.api.dto.InputClienteDTO;
import com.kaibacorp.testetqi.api.dto.InputEmprestimoDTO;
import com.kaibacorp.testetqi.api.dto.LoginDTO;
import com.kaibacorp.testetqi.api.exceptionhandler.ApiExceptionHandler;
import com.kaibacorp.testetqi.builder.EmprestimoDetalhadoBuilder;
import com.kaibacorp.testetqi.builder.InputClienteDTOBuilder;
import com.kaibacorp.testetqi.builder.InputEmprestimoDTOBuilder;
import com.kaibacorp.testetqi.builder.LoginDTOBuilder;
import com.kaibacorp.testetqi.core.mapper.ClienteInputMapper;
import com.kaibacorp.testetqi.core.mapper.EmprestimoDetalhadoMapper;
import com.kaibacorp.testetqi.core.mapper.EmprestimoInputMapper;
import com.kaibacorp.testetqi.domain.exception.BusinessException;
import com.kaibacorp.testetqi.domain.exception.LoginException;
import com.kaibacorp.testetqi.domain.model.Cliente;
import com.kaibacorp.testetqi.domain.model.Emprestimo;
import com.kaibacorp.testetqi.domain.service.ClienteService;
import com.kaibacorp.testetqi.domain.service.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmprestimoControllerTest {

    private static final String EMPRESTIMO_API_URL_PATH = "/api/v1/emprestimos";
    private static final Long VALID_CLIENTE_ID = 1L;
    private static final Long INVALID_EMPRESTIMO_ID = 2L;
    private static final Long VALID_EMPRESTIMO_ID = 1L;

    private MockMvc mockMvc;

    @Mock
    ClienteService clienteService;

    @Mock
    Login login;

    @Mock
    EmprestimoDetalhadoMapper emprestimoDetalhadoMapper;


    @InjectMocks
    EmprestimoController emprestimoController;

    private final EmprestimoInputMapper emprestimoInputMapper = EmprestimoInputMapper.INSTANCE;
    private final ClienteInputMapper clienteInputMapper = ClienteInputMapper.INSTANCE;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(emprestimoController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    void whenGETIsCalledWithValidUserIdAndEmprestimoId_thenOkStatusIsReturned() throws Exception {
        // Condições iniciais
        InputClienteDTO inputClienteDTO = InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente cliente = clienteInputMapper.toModel(inputClienteDTO);
        cliente.setId(VALID_CLIENTE_ID);

        InputEmprestimoDTO inputemprestimoDTO = InputEmprestimoDTOBuilder.builder().build().inputEmprestimoDTO();
        Emprestimo emprestimo = emprestimoInputMapper.toModel(inputemprestimoDTO);
        emprestimo.setId(VALID_EMPRESTIMO_ID);

        var loginDTO = LoginDTOBuilder.builder().build().loginDTOBuilder();

        var output = EmprestimoDetalhadoBuilder.builder().
                build().emprestimoDetalhadoDTObuilder();

        //Estabelecendo comportamento dos Mocks
        when(login.conectar(VALID_CLIENTE_ID,loginDTO)).thenReturn(cliente);
        when(clienteService.findEmprestimo(VALID_CLIENTE_ID,VALID_EMPRESTIMO_ID)).thenReturn(emprestimo);
        when(emprestimoDetalhadoMapper.toDTO(any(),any())).thenReturn(output);
        System.out.println("Passei daqui");

        // Testes
        mockMvc.perform(MockMvcRequestBuilders.get(EMPRESTIMO_API_URL_PATH + "/" + VALID_CLIENTE_ID+ "/" +
                                VALID_EMPRESTIMO_ID).sessionAttr("loginDTO", loginDTO).
                        param("email", loginDTO.getEmail()).
                        param("senha",loginDTO.getSenha()).
                        contentType(MediaType.APPLICATION_JSON)).
                        andExpect(status().isOk());
    }

    @Test
    void whenGETIsCalledWithValidUserIdAndInvalidEmprestimoId_thenExceptionIsReturned() throws Exception {
        // Condições iniciais
        InputClienteDTO inputClienteDTO = InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente cliente = clienteInputMapper.toModel(inputClienteDTO);
        cliente.setId(VALID_CLIENTE_ID);

        InputEmprestimoDTO inputemprestimoDTO = InputEmprestimoDTOBuilder.builder().build().inputEmprestimoDTO();
        Emprestimo emprestimo = emprestimoInputMapper.toModel(inputemprestimoDTO);
        emprestimo.setId(VALID_EMPRESTIMO_ID);

        var loginDTO = LoginDTOBuilder.builder().build().loginDTOBuilder();

        var output = EmprestimoDetalhadoBuilder.builder().
                build().emprestimoDetalhadoDTObuilder();

        //Estabelecendo comportamento dos Mocks
        when(login.conectar(VALID_CLIENTE_ID,loginDTO)).thenReturn(cliente);
        doThrow(BusinessException.class).when(clienteService).findEmprestimo(VALID_CLIENTE_ID,INVALID_EMPRESTIMO_ID);

        // Testes
        mockMvc.perform(MockMvcRequestBuilders.get(EMPRESTIMO_API_URL_PATH + "/" + VALID_CLIENTE_ID+ "/" +
                                INVALID_EMPRESTIMO_ID).sessionAttr("loginDTO", loginDTO).
                        param("email", loginDTO.getEmail()).
                        param("senha",loginDTO.getSenha()).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isBadRequest());
    }

    @Test
    void whenGETIsCalledWithInvaliLogin_thenExceptionIsReturned() throws Exception {
        // Condições iniciais
        var invalidLoginDTO = LoginDTOBuilder.builder().build().loginDTOBuilder();

        var output = EmprestimoDetalhadoBuilder.builder().
                build().emprestimoDetalhadoDTObuilder();

        //Estabelecendo comportamento dos Mocks
        doThrow(LoginException.class).when(login).conectar(VALID_CLIENTE_ID,invalidLoginDTO);

        // Testes
        mockMvc.perform(MockMvcRequestBuilders.get(EMPRESTIMO_API_URL_PATH + "/" + VALID_CLIENTE_ID+ "/" +
                                INVALID_EMPRESTIMO_ID).sessionAttr("invalidLoginDTO", invalidLoginDTO).
                        param("email", invalidLoginDTO.getEmail()).
                        param("senha",invalidLoginDTO.getSenha()).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isBadRequest());
    }
}
