package br.com.zup.zupayments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("compras/")
public class PedidoDeCompraController {

    @Autowired
    private PedidoDeCompraService pedidoDeCompraService;
}