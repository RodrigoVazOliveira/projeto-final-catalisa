package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.fornecedor.entrada.CadastroDeFornecedorDTO;
import br.com.zup.zupayments.enums.CategoriaDeCusto;
import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.services.FornecedorService;
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

@WebMvcTest(FornecedorController.class)
public class FornecedorControllerTest {

    @MockBean
    private FornecedorService fornecedorService;

    @Autowired
    private MockMvc mockMvc;

    private Fornecedor fornecedor;

    private CadastroDeFornecedorDTO cadastroDeFornecedorDTO;

    @BeforeEach
    public void setup() {
        this.fornecedor = new Fornecedor(
                "23.524.377/0001-45",
                "Empresa 1",
                "Rua Dos bobos",
                0,
                "Sem teto",
                "Sem Fim",
                "Sem Estado",
                "2434424232",
                "(99) 9999-9999",
                "rsdfasfdsdf@sfsd.com",
                CategoriaDeCusto.BENEFICIOS,
                true
        );

        this.cadastroDeFornecedorDTO = new CadastroDeFornecedorDTO(
                null,
                "23.524.377/0001-45",
                "Empresa 1",
                "Rua Dos bobos",
                0,
                "Sem teto",
                "Sem Fim",
                "Sem Estado",
                "2434424232",
                "(99) 9999-9999",
                "rsdfasfdsdf@sfsd.com",
                CategoriaDeCusto.BENEFICIOS
        );
    }

    @Test
    public void testarCadastroDeFornecedor() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String fornecedorJson = objectMapper.writeValueAsString(cadastroDeFornecedorDTO);
        String respostaJson   = objectMapper.writeValueAsString(fornecedor);

        Mockito.when(fornecedorService.cadastrarFornecedor(Mockito.any())).thenReturn(fornecedor);

        mockMvc.perform(MockMvcRequestBuilders.post("/fornecedores/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(fornecedorJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(respostaJson));

    }
}
