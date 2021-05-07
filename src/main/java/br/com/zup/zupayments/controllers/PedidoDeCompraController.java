package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.pedidodecompras.entrada.EntradaCadastroPedidoDeCompraDTO;
import br.com.zup.zupayments.dtos.pedidodecompras.entrada.FiltroPedidoDeCompraComNotaFiscalPendenteDTO;
import br.com.zup.zupayments.dtos.pedidodecompras.saida.SaidaCadastroPedidoDeCompraDTO;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.services.PedidoDeCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;

@RestController
@RequestMapping("pedidos/")
public class PedidoDeCompraController {

    private final PedidoDeCompraService pedidoDeCompraService;

    @Autowired
    public PedidoDeCompraController(PedidoDeCompraService pedidoDeCompraService) {
        this.pedidoDeCompraService = pedidoDeCompraService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaidaCadastroPedidoDeCompraDTO cadastrarNovoPedidoDeCompra(
            @RequestBody  EntradaCadastroPedidoDeCompraDTO cadastroPedidoDeCompraDTO) {

        PedidoDeCompra pedidoDeCompra = pedidoDeCompraService.cadastrarNovoPedidoDeCompra(
                cadastroPedidoDeCompraDTO.converterDtoParaModelo()
        );
        return SaidaCadastroPedidoDeCompraDTO.converterModeloParaDto(pedidoDeCompra);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<SaidaCadastroPedidoDeCompraDTO> mostrarTodosPedidoDeCompra() {
        Iterable<PedidoDeCompra> pedidoDeCompras = pedidoDeCompraService.obterTodosOsPedidoDeCompra();
        return SaidaCadastroPedidoDeCompraDTO.converterListaDeModeloParaListaDto(pedidoDeCompras);
    }

    @PatchMapping("{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelarPedidoDeCompra(@PathVariable Long id){
        pedidoDeCompraService.cancelarPedidoDeCompra(id);
    }

    @GetMapping("responsaveis")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<PedidoDeCompra> obterTodosPedidosDeCompraComResponsavelInativo(
            @RequestParam(name = "ativo", defaultValue = "false") Boolean ativo
    ) {
        return pedidoDeCompraService.obterTodosPedidosDeCompraComResponsavelAtivo(ativo);
    }

    @GetMapping("pendentes")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<PedidoDeCompra> obterPedidosComNotaFiscaisPendentesDeEnvio(
            @ModelAttribute FiltroPedidoDeCompraComNotaFiscalPendenteDTO filtro
    ) {
        return pedidoDeCompraService.obterTodosPedidosDeCompraComValorMaiorQueZeroEResponsaveisAtivo(
                filtro.getValorMinimo(), filtro.getAtivo(), filtro.getDataInicial()
        );
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarResponsavelDoPedido(Long numeroDoPedido) {
        pedidoDeCompraService.atualizarResponsavelPorPedidoDeCompra(numeroDoPedido);
    }
        @GetMapping("cobrancas")
        @ResponseStatus(HttpStatus.OK)
        public void enviarEmailDeCobrancas(
                @ModelAttribute FiltroPedidoDeCompraComNotaFiscalPendenteDTO filtro){
            try {
                pedidoDeCompraService.enviarEmailParaPedidosDeCompraComNotasPendentes(
                        filtro.getValorMinimo(), filtro.getAtivo(), filtro.getDataInicial()
                );
            } catch (MessagingException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        }
    }
}