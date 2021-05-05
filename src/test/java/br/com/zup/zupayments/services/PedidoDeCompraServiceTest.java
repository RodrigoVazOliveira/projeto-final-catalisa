package br.com.zup.zupayments.services;

import br.com.zup.zupayments.enums.FormaDePagamento;
import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.repositories.PedidoDeCompraRespository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class PedidoDeCompraServiceTest {

    @Autowired
    private PedidoDeCompraService pedidoDeCompraService;

    @MockBean
    private PedidoDeCompraRespository pedidoDeCompraRespository;

    @MockBean
    private FornecedorService fornecedorService;
    private Fornecedor fornecedor;

    @MockBean
    private ResponsavelService responsavelService;
    private Responsavel responsavel;

    private PedidoDeCompra pedidoDeCompra;

    @BeforeEach
    public void setUp() {
        this.pedidoDeCompra = new PedidoDeCompra();
        this.pedidoDeCompra.setNumeroDePedido(31L);
        this.pedidoDeCompra.setDataDePagamento(LocalDate.now());
        this.pedidoDeCompra.setValorAproximado(2.000);
        this.pedidoDeCompra.setDataDePagamento(LocalDate.now());
        this.pedidoDeCompra.setDataLimiteEnvio(LocalDate.now());
        this.pedidoDeCompra.setFormaDePagamento(FormaDePagamento.BOLETO);
        this.pedidoDeCompra.setDataDeVencimento(LocalDate.now());

        this.responsavel = new Responsavel();
        this.responsavel.setEmail("email@email.com");
        this.pedidoDeCompra.setResponsavel(this.responsavel);

        this.fornecedor = new Fornecedor();
        this.fornecedor.setCnpjOuCpf("084.215.150-80");
        this.pedidoDeCompra.setFornecedor(this.fornecedor);
    }

    @Test
    public void cadastroDePedidoDeCompraTest(){
        Mockito.when(pedidoDeCompraRespository.save(Mockito.any(PedidoDeCompra.class))).thenReturn(pedidoDeCompra);
        PedidoDeCompra pedidoDeCompraTest = pedidoDeCompraService.cadastrarNovoPedidoDeCompra(pedidoDeCompra);
        Assertions.assertEquals(pedidoDeCompraTest,pedidoDeCompra);

        Mockito.when(responsavelService.procurarResponsavelPorEmail(Mockito.any())).thenReturn(responsavel);
        Mockito.when(fornecedorService.pesquisarFornecedorPorCnpjOuCpf(Mockito.any())).thenReturn(fornecedor);
    }

    @Test
    public void obterTodosOsPedidoDeCompraTest(){
        Optional<PedidoDeCompra> optionalPedidoDeCompra =Optional.empty();

        Mockito.when(pedidoDeCompraRespository.findById(Mockito.anyLong())).thenReturn(optionalPedidoDeCompra);

        Assertions.assertThrows(RuntimeException.class,() ->{
            pedidoDeCompraService.obterTodosOsPedidoDeCompra();
            throw new RuntimeException("");
        });
    }
}