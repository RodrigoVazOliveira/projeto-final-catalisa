package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.responsavel.entrada.CadastrarResponsavelDTO;
import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.services.ResponsavelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ResponsavelController.class)
public class ResponsavelControllerTest {

    @MockBean
    private ResponsavelService responsavelService;

    @Autowired
    private MockMvc mockMvc;

    private CadastrarResponsavelDTO cadastrarResponsavelDTO;

    private Responsavel responsavel;

    @BeforeEach
    public void setup() {
        this.cadastrarResponsavelDTO = new CadastrarResponsavelDTO();
        this.cadastrarResponsavelDTO.setEmail("email@email.com");
        this.cadastrarResponsavelDTO.setNomeCompleto("Zup da Silva");
        this.cadastrarResponsavelDTO.setNomeDoProjeto("Zupper");

        this.responsavel = new Responsavel();
        this.responsavel.setAtivo(true);
        this.responsavel.setEmail("email@email.com");
        this.responsavel.setNomeCompleto("Zup da Silva");
        this.responsavel.setNomeDoProjeto("Zupper");
        this.cadastrarResponsavelDTO = new CadastrarResponsavelDTO();
    }

    @Test
    public void testarCadastrarResponsavelOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String entradaJson = objectMapper.writeValueAsString(this.cadastrarResponsavelDTO);
        String saidaJson   = objectMapper.writeValueAsString(this.responsavel);

        Mockito.when(responsavelService.cadastrarResponsavel(Mockito.any())).thenReturn(responsavel);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/responsaveis/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(entradaJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(saidaJson));
    }

    @Test
    public void ativarOuDesativarResponsavel() throws Exception {
        String url = "/responsaveis/?email=rodrigo.vaz@zup.com.br";
        Mockito.doNothing().when(responsavelService).ativarOuDesativarResponsavel(Mockito.anyString());

        mockMvc.perform(MockMvcRequestBuilders.patch(url))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

