package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.pedidodecompras.entrada.EntradaCadastroPedidoDeCompraDTO;
import br.com.zup.zupayments.dtos.pedidodecompras.saida.SaidaCadastroPedidoDeCompraDTO;
import br.com.zup.zupayments.enums.FormaDePagamento;
import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.services.FornecedorService;
import br.com.zup.zupayments.services.PedidoDeCompraService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebMvcTest(PedidoDeCompraController.class)
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
    }

    @Test
    public void testarCadastroPedidoDeCompra() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String cadastroJson = "{\"dataDeVencimento\":\"01/06/2021\",\"valorAproximado\":2.0,\"dataDePagamento\":\"05/05/2021\",\"emailResponsavel\":\"email@email.com\",\"dataLimiteEnvio\":\"01/06/2021\",\"formaDePagamento\":\"BOLETO\",\"cnpjOuCpf\":\"084.215.150-80\"}";
        String receberJson = "{\"numeroDePedido\":1,\"dataDeVencimento\":\"01/06/2021\",\"valorAproximado\":2.0,\"dataDePagamento\":\"05/05/2021\",\"responsavel\":{\"email\":\"email@email.com\",\"nomeCompleto\":null,\"nomeDoProjeto\":null,\"ativo\":null},\"dataLimiteEnvio\":\"01/06/2021\",\"formaDePagamento\":\"BOLETO\",\"fornecedor\":{\"cnpjOuCpf\":\"084.215.150-80\",\"razaoSocial\":null}}";
        Mockito.when(pedidoDeCompraService.cadastrarNovoPedidoDeCompra(Mockito.any())).thenReturn(pedidoDeCompra);

        mockMvc.perform(MockMvcRequestBuilders.post("/pedidos/").
                contentType(MediaType.APPLICATION_JSON).content(cadastroJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(receberJson));
    }
}