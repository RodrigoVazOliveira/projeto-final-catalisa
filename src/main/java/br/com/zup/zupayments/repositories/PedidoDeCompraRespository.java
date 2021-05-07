package br.com.zup.zupayments.repositories;

import br.com.zup.zupayments.models.PedidoDeCompra;
import org.springframework.data.repository.CrudRepository;

public interface PedidoDeCompraRespository extends CrudRepository<PedidoDeCompra, Long> {
    Iterable<PedidoDeCompra> findAllByResponsavelAtivo(Boolean ativo);
    Iterable<PedidoDeCompra> findAllByValorAproximadoGreaterThanAndResponsavelAtivo(Double valorMinimo, Boolean ativo);
}