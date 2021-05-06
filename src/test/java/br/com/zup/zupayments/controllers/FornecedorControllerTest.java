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
        this.fornecedor = new Fornecedor();
        this.fornecedor.setCnpjOuCpf("23.524.377/0001-45");
        this.fornecedor.setRazaoSocial("Empresa 1");
        this.fornecedor.setLogradouro("Rua Dos bobos");
        this.fornecedor.setNumero("0");
        this.fornecedor.setBairro("Sem teto");
        this.fornecedor.setCidade("Sem Fim");
        this.fornecedor.setEstado("Sem Estado");
        this.fornecedor.setCep("2434424232");
        this.fornecedor.setTelefone("(99) 9999-9999");
        this.fornecedor.setEmail("rsdfasfdsdf@sfsd.com");
        this.fornecedor.setCategoriaDeCusto(CategoriaDeCusto.FACILITIES);
        this.fornecedor.setAtivo(true);

        this.cadastroDeFornecedorDTO = new CadastroDeFornecedorDTO();
        this.cadastroDeFornecedorDTO.setCnpj("23.524.377/0001-45");
        this.cadastroDeFornecedorDTO.setRazaoSocial("Empresa 1");
        this.cadastroDeFornecedorDTO.setLogradouro("Rua Dos bobos");
        this.cadastroDeFornecedorDTO.setNumero("0");
        this.cadastroDeFornecedorDTO.setBairro("Sem teto");
        this.cadastroDeFornecedorDTO.setCidade("Sem Fim");
        this.cadastroDeFornecedorDTO.setEstado("Sem Estado");
        this.cadastroDeFornecedorDTO.setCep("2434424232");
        this.cadastroDeFornecedorDTO.setTelefone("(99) 9999-9999");
        this.cadastroDeFornecedorDTO.setEmail("rsdfasfdsdf@sfsd.com");
        this.cadastroDeFornecedorDTO.setCategoriaDeCusto(CategoriaDeCusto.FACILITIES);
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

    @Test
    public void ativarOuDesativarFornecedor() throws Exception {
        String url = "/fornecedores/?cnpjoucpf=23.524.377/0001-45";
        Mockito.doNothing().when(fornecedorService).ativarOuDesativarFornecedor(Mockito.anyString());

        mockMvc.perform(MockMvcRequestBuilders.patch(url))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
