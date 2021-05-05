package br.com.zup.zupayments.services;

import br.com.zup.zupayments.models.NotaFiscal;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.repositories.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotaFiscalService {

    private final NotaFiscalRepository notaFiscalRepository;
    private final ResponsavelService responsavelService;
    private final FornecedorService fornecedorService;
    private final PedidoDeCompraService pedidoDeCompraService;

    @Autowired
    public NotaFiscalService(NotaFiscalRepository notaFiscalRepository, ResponsavelService responsavelService,
                             FornecedorService fornecedorService, PedidoDeCompraService pedidoDeCompraService) {
        this.notaFiscalRepository = notaFiscalRepository;
        this.responsavelService = responsavelService;
        this.fornecedorService = fornecedorService;
        this.pedidoDeCompraService = pedidoDeCompraService;
    }

    public NotaFiscal cadastrarNotaFiscal(NotaFiscal fiscal){
        try{
            fiscal.setResponsavel(responsavelService.procurarResponsavelPorEmail(fiscal.getResponsavel().getEmail()));
            fiscal.setFornecedor(
                    fornecedorService.pesquisarFornecedorPorCnpjOuCpf(fiscal.getFornecedor().getCnpjOuCpf())
            );
            fiscal.setPedidoDeCompra(
                    gerarListaDePedidoDeCompraParaCadastrar(fiscal.getPedidoDeCompra())
            );
            NotaFiscal objFiscal = notaFiscalRepository.save(fiscal);
            return objFiscal;
        }catch (Exception error){
            throw new RuntimeException("Nota fiscal já cadastrada");
        }
    }

    
}
