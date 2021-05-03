package br.com.zup.zupayments.services;

import br.com.zup.zupayments.repositories.PedidoDeCompraRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoDeCompraService {

    @Autowired
    private PedidoDeCompraRespository pedidoDeCompraRespository;
}
