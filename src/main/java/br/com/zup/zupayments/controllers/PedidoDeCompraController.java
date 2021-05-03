package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.services.PedidoDeCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedidos/")
public class PedidoDeCompraController {

    private final PedidoDeCompraService pedidoDeCompraService;

    @Autowired
    public PedidoDeCompraController(PedidoDeCompraService pedidoDeCompraService) {
        this.pedidoDeCompraService = pedidoDeCompraService;
    }
}
