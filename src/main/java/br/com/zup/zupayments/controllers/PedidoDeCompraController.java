package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.pedidodecompras.entrada.AtualizarResponsavelDoPedidoDeCompraDTO;
import br.com.zup.zupayments.dtos.pedidodecompras.entrada.EntradaCadastroPedidoDeCompraDTO;
import br.com.zup.zupayments.dtos.pedidodecompras.entrada.FiltroPedidoDeCompraComNotaFiscalPendenteDTO;
import br.com.zup.zupayments.dtos.pedidodecompras.saida.SaidaCadastroPedidoDeCompraDTO;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.services.PedidoDeCompraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("pedidos/")
@Api(value = "API REST Pedidos de compras")
public class PedidoDeCompraController {

    private final PedidoDeCompraService pedidoDeCompraService;

    @Autowired
    public PedidoDeCompraController(PedidoDeCompraService pedidoDeCompraService) {
        this.pedidoDeCompraService = pedidoDeCompraService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cadastrar um novo pedido de compra")
    public SaidaCadastroPedidoDeCompraDTO cadastrarNovoPedidoDeCompra(
            @RequestBody @Valid EntradaCadastroPedidoDeCompraDTO cadastroPedidoDeCompraDTO) {

        PedidoDeCompra pedidoDeCompra = pedidoDeCompraService.cadastrarNovoPedidoDeCompra(
                cadastroPedidoDeCompraDTO.converterDtoParaModelo()
        );
        return SaidaCadastroPedidoDeCompraDTO.converterModeloParaDto(pedidoDeCompra);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retornar todos os pedidos de compras cadastrados")
    public Iterable<SaidaCadastroPedidoDeCompraDTO> mostrarTodosPedidoDeCompra() {
        Iterable<PedidoDeCompra> pedidoDeCompras = pedidoDeCompraService.obterTodosOsPedidoDeCompra();
        return SaidaCadastroPedidoDeCompraDTO.converterListaDeModeloParaListaDto(pedidoDeCompras);
    }

    @PatchMapping("{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "cancela um pedido de compra pelo seu número de pedido de compra")
    public void cancelarPedidoDeCompra(@PathVariable Long id){
        pedidoDeCompraService.cancelarPedidoDeCompra(id);
    }

    @GetMapping("responsaveis")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna todos os pedidos de compra com responsavel inativo")
    public Iterable<PedidoDeCompra> obterTodosPedidosDeCompraComResponsavelInativo(
            @RequestParam(name = "ativo", defaultValue = "false") Boolean ativo
    ) {
        return pedidoDeCompraService.obterTodosPedidosDeCompraComResponsavelAtivo(ativo);
    }

    @GetMapping("pendentes")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retornar todos os pedidos de compra com nota fiscal nao enviadas")
    public Iterable<PedidoDeCompra> obterPedidosComNotaFiscaisPendentesDeEnvio(
            @ModelAttribute FiltroPedidoDeCompraComNotaFiscalPendenteDTO filtro
    ) {
        return pedidoDeCompraService.obterTodosPedidosDeCompraComValorMaiorQueZeroEResponsaveisAtivo(
                filtro.getValorMinimo(), filtro.getAtivo(), filtro.getDataInicial()
        );
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Atualizar o pedido de compra com responsavel inativo")
    public void alterarResponsavelDoPedido(
            @RequestBody AtualizarResponsavelDoPedidoDeCompraDTO atualizarResponsavelDoPedidoDeCompraDTO) {
        pedidoDeCompraService.atualizarResponsavelPorPedidoDeCompra(atualizarResponsavelDoPedidoDeCompraDTO);
    }

    @GetMapping("cobrancas")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Envia e-mail para o responsável do pedido de compra que possuem pendencia de nota fiscal")
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