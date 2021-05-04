package br.com.zup.zupayments.services;

import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.repositories.FornecedorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class FornecedorServiceTest {

    @Autowired
    private FornecedorService fornecedorService;

    @MockBean
    private FornecedorRepository fornecedorRepository;

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

    @Test
    public void testarCadastroDeFornecedorOk() {
        Mockito.when(fornecedorRepository.save(Mockito.any(Fornecedor.class))).thenReturn(fornecedor);
        Fornecedor testFornecedor = fornecedorService.cadastrarFornecedor(fornecedor);
        Assertions.assertEquals(testFornecedor, fornecedor);
    }
}
