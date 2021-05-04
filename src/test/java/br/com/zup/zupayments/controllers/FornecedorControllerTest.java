package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.services.FornecedorService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class FornecedorControllerTest {

    @MockBean
    private FornecedorService fornecedorService;

    @Autowired
    private MockMvc mockMvc;

    private Fornecedor fornecedor;

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
                null
        );
    }
}
