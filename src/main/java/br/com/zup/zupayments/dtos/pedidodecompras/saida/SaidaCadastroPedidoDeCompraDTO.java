package br.com.zup.zupayments.dtos.pedidodecompras.saida;

import br.com.zup.zupayments.enums.FormaDePagamento;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.models.Responsavel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaidaCadastroPedidoDeCompraDTO {

    private Long numeroDePedido;

    @JsonFormat(pattern = "dd/mm/YYYY")
    private LocalDate dataDeVencimento;
    private Double valorAproximado;

    @JsonFormat(pattern = "dd/mm/YYYY")
    private LocalDate dataDePagamento;
    private Responsavel responsavel;

    @JsonFormat(pattern = "dd/mm/YYYY")
    private LocalDate dataLimiteEnvio;
    private FormaDePagamento formaDePagamento;

    private PedidoDeCompraFornecedorDTO fornecedor;

    public SaidaCadastroPedidoDeCompraDTO() {
    }

    public SaidaCadastroPedidoDeCompraDTO(Long numeroDePedido, LocalDate dataDeVencimento, Double valorAproximado,
                                          LocalDate dataDePagamento, Responsavel responsavel, LocalDate dataLimiteEnvio,
                                          FormaDePagamento formaDePagamento, PedidoDeCompraFornecedorDTO fornecedor) {
        this.numeroDePedido = numeroDePedido;
        this.dataDeVencimento = dataDeVencimento;
        this.valorAproximado = valorAproximado;
        this.dataDePagamento = dataDePagamento;
        this.responsavel = responsavel;
        this.dataLimiteEnvio = dataLimiteEnvio;
        this.formaDePagamento = formaDePagamento;
        this.fornecedor = fornecedor;
    }

    public Long getNumeroDePedido() {
        return numeroDePedido;
    }

    public void setNumeroDePedido(Long numeroDePedido) {
        this.numeroDePedido = numeroDePedido;
    }

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }

    public Double getValorAproximado() {
        return valorAproximado;
    }

    public void setValorAproximado(Double valorAproximado) {
        this.valorAproximado = valorAproximado;
    }

    public LocalDate getDataDePagamento() {
        return dataDePagamento;
    }

    public void setDataDePagamento(LocalDate dataDePagamento) {
        this.dataDePagamento = dataDePagamento;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public LocalDate getDataLimiteEnvio() {
        return dataLimiteEnvio;
    }

    public void setDataLimiteEnvio(LocalDate dataLimiteEnvio) {
        this.dataLimiteEnvio = dataLimiteEnvio;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public PedidoDeCompraFornecedorDTO getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(PedidoDeCompraFornecedorDTO fornecedor) {
        this.fornecedor = fornecedor;
    }

    public static SaidaCadastroPedidoDeCompraDTO converterModeloParaDto(PedidoDeCompra pedidoDeCompra) {
        return new SaidaCadastroPedidoDeCompraDTO(
                pedidoDeCompra.getNumeroDePedido(),
                pedidoDeCompra.getDataDeVencimento(),
                pedidoDeCompra.getValorAproximado(),
                pedidoDeCompra.getDataDePagamento(),
                pedidoDeCompra.getResponsavel(),
                pedidoDeCompra.getDataLimiteEnvio(),
                pedidoDeCompra.getFormaDePagamento(),
                PedidoDeCompraFornecedorDTO.converterModeloParaDto(pedidoDeCompra.getFornecedor())
        );
    }

    public static Iterable<SaidaCadastroPedidoDeCompraDTO> converterListaDeModeloParaListaDto(
            Iterable<PedidoDeCompra> pedidoDeCompras) {
        List<SaidaCadastroPedidoDeCompraDTO> dtos = new ArrayList<>();

        for (PedidoDeCompra pedidoDeCompra : pedidoDeCompras) {
            dtos.add(converterModeloParaDto(pedidoDeCompra));
        }

        return dtos;
    }
}
