package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.pedidodecompras.entrada.EntradaCadastroPedidoDeCompraDTO;
import br.com.zup.zupayments.dtos.pedidodecompras.entrada.FiltroPedidoDeCompraComNotaFiscalPendenteDTO;
import br.com.zup.zupayments.dtos.pedidodecompras.saida.SaidaCadastroPedidoDeCompraDTO;
import br.com.zup.zupayments.enums.FormaDePagamento;
import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.services.FornecedorService;
import br.com.zup.zupayments.services.PedidoDeCompraService;
import br.com.zup.zupayments.services.ResponsavelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(PedidoDeCompraController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PedidoDeCompraControllerTest {

    @MockBean
    private PedidoDeCompraService pedidoDeCompraService;

    @MockBean
    private FornecedorService fornecedorService;
    private Fornecedor fornecedor;

    @MockBean
    private ResponsavelService responsavelService;
    private Responsavel responsavel;

    @Autowired
    private MockMvc mockMvc;

    private PedidoDeCompra pedidoDeCompra;
    private EntradaCadastroPedidoDeCompraDTO entradaCadastroPedidoDeCompraDTO;
    private SaidaCadastroPedidoDeCompraDTO saidaCadastroPedidoDeCompraDTO;
    private List<PedidoDeCompra> pedidoDeCompras;

    @BeforeEach
    public void setUp(){
        this.entradaCadastroPedidoDeCompraDTO = new EntradaCadastroPedidoDeCompraDTO();
        this.entradaCadastroPedidoDeCompraDTO.setCnpjOuCpf("084.215.150-80");

        DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.entradaCadastroPedidoDeCompraDTO.setDataDeVencimento(LocalDate.parse("2021-06-01", dtf));
        this.entradaCadastroPedidoDeCompraDTO.setDataDePagamento(LocalDate.parse("2021-05-05", dtf));
        this.entradaCadastroPedidoDeCompraDTO.setDataLimiteEnvio(LocalDate.parse("2021-06-01", dtf));
        this.entradaCadastroPedidoDeCompraDTO.setFormaDePagamento(FormaDePagamento.BOLETO);
        this.entradaCadastroPedidoDeCompraDTO.setEmailResponsavel("email@email.com");
        this.entradaCadastroPedidoDeCompraDTO.setValorAproximado(2.000);

        this.pedidoDeCompra = this.entradaCadastroPedidoDeCompraDTO.converterDtoParaModelo();
        this.pedidoDeCompra.setNumeroDePedido(1L);

        this.saidaCadastroPedidoDeCompraDTO = SaidaCadastroPedidoDeCompraDTO.converterModeloParaDto(this.pedidoDeCompra);

        this.pedidoDeCompras = new ArrayList<>();

        for (Long i = 0L; i < 2; i++) {
            pedidoDeCompras.add(criarNovoPedido(i));
        }
    }

    private PedidoDeCompra criarNovoPedido(Long numeroDePedido) {
        PedidoDeCompra pedido = new PedidoDeCompra();
        pedido.setNumeroDePedido(31L);
        pedido.setDataDePagamento(LocalDate.parse("2021-05-01"));
        pedido.setValorAproximado(2.000);
        pedido.setDataDePagamento(LocalDate.parse("2021-05-01"));
        pedido.setDataLimiteEnvio(LocalDate.parse("2021-05-01"));
        pedido.setFormaDePagamento(FormaDePagamento.BOLETO);
        pedido.setDataDeVencimento(LocalDate.parse("2021-05-01"));

        Responsavel responsavelTestLista = new Responsavel();
        responsavelTestLista.setEmail("email@email.com");
        responsavelTestLista.setNomeCompleto("Rodrigo Vaz");
        responsavelTestLista.setNomeDoProjeto("FACILITIES");
        pedido.setResponsavel(responsavelTestLista);

        Fornecedor fornecedorTest = new Fornecedor();
        fornecedorTest.setCnpjOuCpf("084.215.150-80");
        pedido.setFornecedor(fornecedorTest);

        return pedido;
    }

    @Test
    public void testarCadastroPedidoDeCompra() throws Exception{
        String cadastroJson = "{\"dataDeVencimento\":\"01/06/2021\",\"valorAproximado\":2.0,\"dataDePagamento\":\"05/05/2021\",\"emailResponsavel\":\"email@email.com\",\"dataLimiteEnvio\":\"01/06/2021\",\"formaDePagamento\":\"BOLETO\",\"cnpjOuCpf\":\"084.215.150-80\"}";
        String receberJson = "{\"numeroDePedido\":1,\"dataDeVencimento\":\"01/06/2021\",\"valorAproximado\":2.0,\"dataDePagamento\":\"05/05/2021\",\"responsavel\":{\"email\":\"email@email.com\",\"nomeCompleto\":null,\"nomeDoProjeto\":null,\"ativo\":null},\"dataLimiteEnvio\":\"01/06/2021\",\"formaDePagamento\":\"BOLETO\",\"fornecedor\":{\"cnpjOuCpf\":\"084.215.150-80\",\"razaoSocial\":null}}";
        Mockito.when(pedidoDeCompraService.cadastrarNovoPedidoDeCompra(Mockito.any())).thenReturn(pedidoDeCompra);

        mockMvc.perform(MockMvcRequestBuilders.post("/pedidos/").
                contentType(MediaType.APPLICATION_JSON).content(cadastroJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(receberJson));
    }

    @Test
    public void testarObterTodosPedidosDeCompraComResponsavelInativo() throws Exception {
        Mockito.when(
                    pedidoDeCompraService
                    .obterTodosPedidosDeCompraComResponsavelAtivo(
                            Mockito.anyBoolean()
                    )
        ).thenReturn(this.pedidoDeCompras);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);

        String jsonResposta = objectMapper.writeValueAsString(this.pedidoDeCompras);

        System.out.println(jsonResposta);

        mockMvc.perform(MockMvcRequestBuilders.get("/pedidos/responsaveis?ativo=true"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(jsonResposta));
    }

    @Test
    public void testarObterPedidosComNotaFiscaisPendentesDeEnvio() throws Exception{
        Mockito.when(
                pedidoDeCompraService.obterTodosPedidosDeCompraComValorMaiorQueZeroEResponsaveisAtivo
                                (Mockito.anyDouble(), Mockito.anyBoolean(), Mockito.any()))
                .thenReturn(this.pedidoDeCompras);

        SimpleDateFormat nfPendente = new SimpleDateFormat("dd-MM-yyyy");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        objectMapper.setDateFormat(nfPendente);

        String retornoJson = objectMapper.writeValueAsString(this.pedidoDeCompras);

        mockMvc.perform(MockMvcRequestBuilders.get("/pedidos/pendentes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(retornoJson));
    }
}