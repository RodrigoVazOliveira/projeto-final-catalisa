package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.pedidodecompras.entrada.EntradaCadastroPedidoDeCompraDTO;
import br.com.zup.zupayments.services.PedidoDeCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
            @RequestBody @Valid EntradaCadastroPedidoDeCompraDTO cadastroPedidoDeCompra) {

    }
}
