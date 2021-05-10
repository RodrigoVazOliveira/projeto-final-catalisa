package br.com.zup.zupayments.dtos.pedidodecompras.saida;

import br.com.zup.zupayments.enums.FormaDePagamento;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.models.Responsavel;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaidaCadastroPedidoDeCompraDTO {

    private Long numeroDePedido;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeVencimento;
    private Double saldo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDePagamento;

    @NotBlank(message = "{validacao.nome_completo}")
    private Responsavel responsavel;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLimiteEnvio;
    private FormaDePagamento formaDePagamento;

    private PedidoDeCompraFornecedorDTO fornecedor;

    public SaidaCadastroPedidoDeCompraDTO() {
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

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
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
        SaidaCadastroPedidoDeCompraDTO dto = new SaidaCadastroPedidoDeCompraDTO();
        dto.setNumeroDePedido(pedidoDeCompra.getNumeroDePedido());
        dto.setDataDeVencimento(pedidoDeCompra.getDataDeVencimento());
        dto.setSaldo(pedidoDeCompra.getSaldo());
        dto.setDataDePagamento(pedidoDeCompra.getDataDePagamento());
        dto.setResponsavel(pedidoDeCompra.getResponsavel());
        dto.setDataLimiteEnvio(pedidoDeCompra.getDataLimiteEnvio());
        dto.setFormaDePagamento(pedidoDeCompra.getFormaDePagamento());
        dto.setFornecedor(PedidoDeCompraFornecedorDTO.converterModeloParaDto(pedidoDeCompra.getFornecedor()));
        return dto;
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
