package br.com.zup.zupayments.dtos.pedidodecompras.entrada;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class FiltroPedidoDeCompraComNotaFiscalPendenteDTO {
    private Double valorMinimo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInicial;
    private Boolean ativo;

    public FiltroPedidoDeCompraComNotaFiscalPendenteDTO() {
    }

    public Double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(Double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
