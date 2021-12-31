package com.kaibacorp.testetqi.controller;

import com.kaibacorp.testetqi.api.controller.ClienteController;
import com.kaibacorp.testetqi.api.dto.InputClienteDTO;
import com.kaibacorp.testetqi.api.dto.ResponseDTO;
import com.kaibacorp.testetqi.api.exceptionhandler.ApiExceptionHandler;
import com.kaibacorp.testetqi.builder.InputClienteDTOBuilder;
import com.kaibacorp.testetqi.core.mapper.ClienteInputMapper;
import com.kaibacorp.testetqi.domain.exception.DontFoundException;
import com.kaibacorp.testetqi.domain.model.Cliente;
import com.kaibacorp.testetqi.domain.repository.ClienteRepository;
import com.kaibacorp.testetqi.domain.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static utils.JsonConvertionUtils.asJsonString;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClienteControllerTest {

    private static final String CLIENTE_API_URL_PATH = "/api/v1/clientes";
    private static final Long VALID_CLIENTE_ID = 1L;
    private static final Long INVALID_CLIENTE_ID = 2L;

    private MockMvc mockMvc;

    private final ClienteInputMapper clienteInputMapper = ClienteInputMapper.INSTANCE;


    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    void whenPOSTIsCalled_ThenAClienteIsCreated() throws Exception {
        // Condições iniciais
        InputClienteDTO clienteInput = InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente cliente =  clienteInputMapper.toModel(clienteInput);

        // Testes
        mockMvc.perform(post(CLIENTE_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clienteInput)))
                        .andExpect(status().isCreated());
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredField_thenAnErrorIsReturned() throws Exception {
        // Condições iniciais
        InputClienteDTO inputClienteDTO = InputClienteDTOBuilder.builder().build().toInputClientDTO();
        inputClienteDTO.setCpf(null);

        // Testes
        mockMvc.perform(post(CLIENTE_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputClienteDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPOSTIsCalledWithInvalidField_thenAnErrorIsReturned() throws Exception {
        // Condições Iniciais
        InputClienteDTO inputClienteDTO = InputClienteDTOBuilder.builder().build().toInputClientDTO();
        inputClienteDTO.setCpf("0949230");

        // Teste
        mockMvc.perform(post(CLIENTE_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputClienteDTO)))
                .andExpect(status().isBadRequest());
    }

    private ResponseDTO menssagemResposta(String msg) {
        return ResponseDTO.builder().
                message(msg).
                build();
    }

    @Test
    void whenGETIsCalledWithValidId_thenOkStatusIsReturned() throws Exception {
        // Condições iniciais
        InputClienteDTO inputClienteDTO = InputClienteDTOBuilder.builder().build().toInputClientDTO();
        Cliente cliente = clienteInputMapper.toModel(inputClienteDTO);
        cliente.setId(VALID_CLIENTE_ID);

        //Estabelecendo comportamento dos Mocks
        when(clienteService.findByClientId(VALID_CLIENTE_ID)).thenReturn(cliente);

        // Testes
        mockMvc.perform(MockMvcRequestBuilders.get(CLIENTE_API_URL_PATH + "/" + VALID_CLIENTE_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void whenGETIsCalledWithInvalidId_thenDontFoundExceptionIsReturned() throws Exception{
        //Estabelecendo comportamento dos Mocks
        doThrow(DontFoundException.class).when(clienteService).findByClientId(INVALID_CLIENTE_ID);

        // Testes
        mockMvc.perform(MockMvcRequestBuilders.get(CLIENTE_API_URL_PATH + "/" + INVALID_CLIENTE_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());
    }
}
