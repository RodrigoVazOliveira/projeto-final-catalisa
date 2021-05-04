package br.com.zup.zupayments.services;

import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.repositories.FornecedorRepository;
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
}
