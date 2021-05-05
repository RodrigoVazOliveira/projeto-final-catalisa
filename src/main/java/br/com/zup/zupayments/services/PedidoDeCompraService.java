package br.com.zup.zupayments.services;

import br.com.zup.zupayments.exceptions.erros.PedidoDeCompraNaoCadastradoException;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.repositories.PedidoDeCompraRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoDeCompraService {

    private PedidoDeCompraRespository pedidoDeCompraRespository;
    private final ResponsavelService responsavelService;
    private final FornecedorService fornecedorService;

    @Autowired
    public PedidoDeCompraService(PedidoDeCompraRespository pedidoDeCompraRespository,
                                 ResponsavelService responsavelService,
                                 FornecedorService fornecedorService) {
        this.pedidoDeCompraRespository = pedidoDeCompraRespository;
        this.responsavelService = responsavelService;
        this.fornecedorService = fornecedorService;
    }

    public PedidoDeCompra cadastrarNovoPedidoDeCompra(PedidoDeCompra pedidoDeCompra) {
        pedidoDeCompra.setResponsavel(responsavelService.procurarResponsavelPorEmail(
                pedidoDeCompra.getResponsavel().getEmail()));
        pedidoDeCompra.setFornecedor(fornecedorService.pesquisarFornecedorPorCnpjOuCpf(
                pedidoDeCompra.getFornecedor().getCnpjOuCpf()));
        return pedidoDeCompraRespository.save(pedidoDeCompra);
    }

    public Iterable<PedidoDeCompra> obterTodosOsPedidoDeCompra() {
        return pedidoDeCompraRespository.findAll();
    }

    public PedidoDeCompra procurarPedidoDeCompraPeloNumeroDePedido(Long numeroPedidoDeCompra) {
        Optional<PedidoDeCompra> optionalPedidoDeCompra = pedidoDeCompraRespository.findById(numeroPedidoDeCompra);

        if (optionalPedidoDeCompra.isEmpty()) {
            throw new RuntimeException("Não existe pedido de compra com o número " + numeroPedidoDeCompra);
        }

        return optionalPedidoDeCompra.get();
    }
    public PedidoDeCompra cancelarPedidoDeCompra (Long id, PedidoDeCompra pedidoDeCompra){
        Optional<PedidoDeCompra> pedidoDeCompraOptional = pedidoDeCompraRespository.findById(id);

        if (pedidoDeCompraOptional.isEmpty())
            throw new PedidoDeCompraNaoCadastradoException();

        pedidoDeCompra.setCancelado(true);
        pedidoDeCompraRespository.save(pedidoDeCompra);

        return pedidoDeCompra;

    }

}
