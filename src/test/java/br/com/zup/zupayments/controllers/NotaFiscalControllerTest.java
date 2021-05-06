package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.models.NotaFiscal;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.services.NotaFiscalService;
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

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@WebMvcTest(NotaFiscalController.class)
public class NotaFiscalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotaFiscalService notaFiscalService;

    private NotaFiscal notaFiscalteste;
    private Responsavel responsavel;
    private Fornecedor fornecedor;
    private PedidoDeCompra pedidoDeCompra;

    @BeforeEach
    public void setup() {
        this.notaFiscalteste = new NotaFiscal();

        DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.responsavel  = new Responsavel();
        this.responsavel.setAtivo(true);
        this.responsavel.setEmail("bomdia@zup.com.br");
        this.responsavel.setNomeCompleto("bom dia silva");

        this.fornecedor = new Fornecedor();
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

        this.pedidoDeCompra = new PedidoDeCompra();
        this.pedidoDeCompra.setNumeroDePedido(1L);

        this.notaFiscalteste.setFornecedor(fornecedor);
        this.notaFiscalteste.setResponsavel(this.responsavel);
        this.notaFiscalteste.setPedidoDeCompra(Arrays.asList(this.pedidoDeCompra));
        this.notaFiscalteste.setValorAPagar(300.50);
        this.notaFiscalteste.setDataDeEmissao(LocalDate.now(Clock.systemUTC()));
        this.notaFiscalteste.setNumeroDaNota(4L);
        this.notaFiscalteste.setDataDeEnvio(LocalDate.now());

    }


    @Test
    public void testarCadastrarNotaFiscal() throws Exception {
        this.notaFiscalteste.setId(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonEntrada = "{\"numeroDaNota\":12, \"cnpjOuCpfFornecedor\":\"03.497.698/0001-90\",\"valorAPagar\":200.00, \"dataDeEmissao\":\"06/05/2021\",\"pedidoDeCompras\":[1,2, 3],\"dataDeEnvio\":\"06/05/2021\", \"emailDoResponsavel\":\"rodrigo.vaz@zup.com.br\"}";
        String jsonSaida   = "{\"id\":1 ,\"numeroDaNota\":4,\"fornecedor\":{\"logradouro\":\"Rua da empresa\",\"numero\":272,\"bairro\":\"Bairro da empresa\",\"cidade\":null,\"estado\":\"Estado da empresa\",\"cep\":\"12122976\",\"telefone\":null,\"email\":\"ceo@umaempresa.com\",\"categoriaDeCusto\":null,\"ativo\":true,\"razaoSocial\":\"Uma Empresa Ltda\",\"cnpjOuCpf\":\"12.234.543/8765-56\"},\"valorAPagar\":300.5,\"dataDeEmissao\":\"2021-05-06\",\"pedidoDeCompra\":[{\"numeroDePedido\":1,\"dataDeVencimento\":null,\"valorAproximado\":null,\"dataDePagamento\":null,\"responsavel\":null,\"dataLimiteEnvio\":null,\"formaDePagamento\":null,\"fornecedor\":null,\"cancelado\":null}],\"dataDeEnvio\":\"2021-05-06\",\"responsavel\":{\"email\":\"bomdia@zup.com.br\",\"nomeCompleto\":\"bom dia silva\",\"nomeDoProjeto\":null,\"ativo\":true},\"cancelar\":null}";

        Mockito.when(notaFiscalService.cadastrarNotaFiscal(Mockito.any())).thenReturn(notaFiscalteste);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/notas_fiscais/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntrada)
        ).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(jsonSaida));
    }

    @Test
    public void testarCancelamentoDeNotaFiscal() throws Exception {
        this.notaFiscalteste.setId(1L);
        this.notaFiscalteste.setCancelar(false);
        String jsonSaida   = "{\"id\":1 ,\"numeroDaNota\":4,\"fornecedor\":{\"logradouro\":\"Rua da empresa\",\"numero\":272,\"bairro\":\"Bairro da empresa\",\"cidade\":null,\"estado\":\"Estado da empresa\",\"cep\":\"12122976\",\"telefone\":null,\"email\":\"ceo@umaempresa.com\",\"categoriaDeCusto\":null,\"ativo\":true,\"razaoSocial\":\"Uma Empresa Ltda\",\"cnpjOuCpf\":\"12.234.543/8765-56\"},\"valorAPagar\":300.5,\"dataDeEmissao\":\"2021-05-06\",\"pedidoDeCompra\":[{\"numeroDePedido\":1,\"dataDeVencimento\":null,\"valorAproximado\":null,\"dataDePagamento\":null,\"responsavel\":null,\"dataLimiteEnvio\":null,\"formaDePagamento\":null,\"fornecedor\":null,\"cancelado\":null}],\"dataDeEnvio\":\"2021-05-06\",\"responsavel\":{\"email\":\"bomdia@zup.com.br\",\"nomeCompleto\":\"bom dia silva\",\"nomeDoProjeto\":null,\"ativo\":true},\"cancelar\": false}";
        Mockito.when(notaFiscalService.cancelarNF(Mockito.anyLong())).thenReturn(this.notaFiscalteste);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/notas_fiscais/1/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(jsonSaida));
    }
}
