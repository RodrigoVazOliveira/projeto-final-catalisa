package br.com.zup.zupayments.services;

import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.models.NotaFiscal;
import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.repositories.NotaFiscalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

@SpringBootTest
public class NotaFiscalServiceTest {

    @Autowired
    private NotaFiscalService notaFiscalService;

    @MockBean
    private NotaFiscalRepository notaFiscalRepository;
    private NotaFiscal notaFiscalteste;

    @BeforeEach
        public void setup(){

        this.notaFiscalteste = new NotaFiscal();

        Responsavel notafiscal = new Responsavel();
        notafiscal.setAtivo(true);
        notafiscal.setEmail("bomdia@zup.com.br");
        notafiscal.setNomeCompleto("bom dia silva");

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setRazaoSocial("Uma Empresa Ltda");
        fornecedor.setAtivo(true);
        fornecedor.setCategoriaDeCusto(null);
        fornecedor.setEmail("ceo@umaempresa.com");
        fornecedor.setCnpjOuCpf("12.234.543/8765-56");
        fornecedor.setLogradouro("Rua da empresa");
        fornecedor.setNumero(272);
        fornecedor.setCep("12122976");
        fornecedor.setBairro("Bairro da empresa");
        fornecedor.setEstado("Estado da empresa");

        this.notaFiscalteste.setId(1l);
                this.notaFiscalteste.setNumeroDaNota(220904l);
                this.notaFiscalteste.setFornecedor(fornecedor);
                this.notaFiscalteste.setValorAPagar(300.50);
                this.notaFiscalteste.setDataDeEmissao(LocalDate.now());
                this.notaFiscalteste.setNumeroDaNota(4L);
                this.notaFiscalteste.setDataDeEnvio(LocalDate.now());
                this.notaFiscalteste.setResponsavel(notafiscal);
    }

    @Test
    public void testarCadastroDeResponsavelOk() {
        Mockito.when(notaFiscalRepository.save(Mockito.any(NotaFiscal.class))).thenReturn(notaFiscalteste);
        NotaFiscal testNota = notaFiscalService.cadastrarNotaFiscal(notaFiscalteste);
        Assertions.assertEquals(testNota, notaFiscalteste);
    }
}
