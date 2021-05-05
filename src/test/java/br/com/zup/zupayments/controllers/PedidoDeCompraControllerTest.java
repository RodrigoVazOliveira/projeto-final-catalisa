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
        this.pedidoDeCompra = new PedidoDeCompra();
        this.pedidoDeCompra.setNumeroDePedido(31L);
        this.pedidoDeCompra.setDataDePagamento(LocalDate.now());
        this.pedidoDeCompra.setValorAproximado(2.000);
        this.pedidoDeCompra.setDataDePagamento(LocalDate.now());
        this.pedidoDeCompra.setDataLimiteEnvio(LocalDate.now());
        this.pedidoDeCompra.setFormaDePagamento(FormaDePagamento.BOLETO);
        this.pedidoDeCompra.setDataDeVencimento(LocalDate.now());

        this.entradaCadastroPedidoDeCompraDTO = new EntradaCadastroPedidoDeCompraDTO();
        this.entradaCadastroPedidoDeCompraDTO.setCnpjOuCpf("084.215.150-80");
        this.entradaCadastroPedidoDeCompraDTO.setDataDeVencimento(LocalDate.now());
        this.entradaCadastroPedidoDeCompraDTO.setDataDePagamento(LocalDate.now());
        this.entradaCadastroPedidoDeCompraDTO.setDataLimiteEnvio(LocalDate.now());
        this.entradaCadastroPedidoDeCompraDTO.setFormaDePagamento(FormaDePagamento.BOLETO);
        this.entradaCadastroPedidoDeCompraDTO.setEmailResponsavel("email@email.com");
        this.entradaCadastroPedidoDeCompraDTO.setValorAproximado(2.000);

        this.saidaCadastroPedidoDeCompraDTO = new SaidaCadastroPedidoDeCompraDTO();
        this.saidaCadastroPedidoDeCompraDTO.setNumeroDePedido(31L);
        this.saidaCadastroPedidoDeCompraDTO.setDataDePagamento(LocalDate.now());
        this.saidaCadastroPedidoDeCompraDTO.setDataDeVencimento(LocalDate.now());
        this.saidaCadastroPedidoDeCompraDTO.setDataLimiteEnvio(LocalDate.now());
        this.saidaCadastroPedidoDeCompraDTO.setValorAproximado(2.000);

        this.responsavel = new Responsavel();
        this.responsavel.setEmail("email@email.com");
        this.saidaCadastroPedidoDeCompraDTO.setResponsavel(this.responsavel);
    }

    @Test
    public void testarCadastroPedidoDeCompra() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String cadastroJson = objectMapper.writeValueAsString(entradaCadastroPedidoDeCompraDTO);
        String receberJson = objectMapper.writeValueAsString(saidaCadastroPedidoDeCompraDTO);

        Mockito.when(pedidoDeCompraService.cadastrarNovoPedidoDeCompra(Mockito.any())).thenReturn(pedidoDeCompra);

        mockMvc.perform(MockMvcRequestBuilders.post("/pedidos/").
                contentType(MediaType.APPLICATION_JSON).content(cadastroJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(receberJson));
    }
}