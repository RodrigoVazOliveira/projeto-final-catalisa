package br.com.zup.zupayments.dtos.pedidodecompras.entrada;

public class AtualizarResponsavelDoPedidoDeCompraDTO {
    private Long numeroPedidoDeCompra;
    private String emailResponsavel;

    public AtualizarResponsavelDoPedidoDeCompraDTO() {
    }

    public Long getNumeroPedidoDeCompra() {
        return numeroPedidoDeCompra;
    }

    public void setNumeroPedidoDeCompra(Long numeroPedidoDeCompra) {
        this.numeroPedidoDeCompra = numeroPedidoDeCompra;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }
}
