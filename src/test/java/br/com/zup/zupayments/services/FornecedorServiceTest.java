package br.com.zup.zupayments.services;

import br.com.zup.zupayments.enums.CategoriaDeCusto;
import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.repositories.FornecedorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class FornecedorServiceTest {

    @Autowired
    private FornecedorService fornecedorService;

    @MockBean
    private FornecedorRepository fornecedorRepository;

    private Fornecedor fornecedor;

    @BeforeEach
    public void setup() {
        this.fornecedor = new Fornecedor();
        this.fornecedor.setCnpjOuCpf("23.524.377/0001-45");
        this.fornecedor.setRazaoSocial("Empresa 1");
        this.fornecedor.setLogradouro("Rua Dos bobos");
        this.fornecedor.setNumero(0);
        this.fornecedor.setBairro("Sem teto");
        this.fornecedor.setCidade("Sem Fim");
        this.fornecedor.setEstado("Sem Estado");
        this.fornecedor.setCep("2434424232");
        this.fornecedor.setTelefone("(99) 9999-9999");
        this.fornecedor.setEmail("rsdfasfdsdf@sfsd.com");
        this.fornecedor.setCategoriaDeCusto(CategoriaDeCusto.FACILITIES);
        this.fornecedor.setAtivo(true);
    }

    @Test
    public void testarCadastroDeFornecedorOk() {
        Mockito.when(fornecedorRepository.save(Mockito.any(Fornecedor.class))).thenReturn(fornecedor);
        Fornecedor testFornecedor = fornecedorService.cadastrarFornecedor(fornecedor);
        Assertions.assertEquals(testFornecedor, fornecedor);
    }

    @Test
    public void testarPesquisarFornecedorPorCnpjOuCpfOk() {
        Optional<Fornecedor> optionalFornecedor = Optional.of(fornecedor);
        Mockito.when(fornecedorRepository.findById(Mockito.anyString())).thenReturn(optionalFornecedor);
        Fornecedor test = fornecedorService.pesquisarFornecedorPorCnpjOuCpf("23.524.377/0001-45");
        Assertions.assertEquals(test, fornecedor);
    }

    @Test
    public void testarPesquisarFornecedorPorCnpjOuCpfError() {
        Optional<Fornecedor> optionalFornecedor = Optional.empty();
        Mockito.when(fornecedorRepository.findById(Mockito.anyString())).thenReturn(optionalFornecedor);
        Assertions.assertThrows(RuntimeException.class, () -> {
            fornecedorService.pesquisarFornecedorPorCnpjOuCpf("23.524.377/0001-45");
        });
    }

    @Test
    public void testarAtualizarCadastroFornecedor() {
        Optional<Fornecedor> optionalFornecedor = Optional.of(fornecedor);
        Mockito.when(fornecedorRepository.findById(Mockito.anyString())).thenReturn(optionalFornecedor);
        Mockito.when(fornecedorRepository.save(Mockito.any(Fornecedor.class))).thenReturn(fornecedor);

        Fornecedor test = fornecedorService.atualizarCadastroFornecedor("23.524.377/0001-45", fornecedor);

        Assertions.assertEquals(test, fornecedor);
    }

    @Test
    public void TestardeletarFornecedorOk() {
        Mockito.doNothing().when(fornecedorRepository).deleteById(Mockito.anyString());
        fornecedorService.deletarFornecedor("234234");
        Mockito.verify(fornecedorRepository, Mockito.times(1)).deleteById(Mockito.anyString());
    }
}
