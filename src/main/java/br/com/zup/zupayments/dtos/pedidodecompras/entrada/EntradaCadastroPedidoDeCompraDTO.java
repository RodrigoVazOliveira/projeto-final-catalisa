package br.com.zup.zupayments.dtos.pedidodecompras.entrada;

import br.com.zup.zupayments.enums.FormaDePagamento;
import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.models.Responsavel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class EntradaCadastroPedidoDeCompraDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeVencimento;
    private Double valorAproximado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDePagamento;
    private String emailResponsavel;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataLimiteEnvio;
    private FormaDePagamento formaDePagamento;
    private String cnpjOuCpf;

    public EntradaCadastroPedidoDeCompraDTO() {
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

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
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

    public String getCnpjOuCpf() {
        return cnpjOuCpf;
    }

    public void setCnpjOuCpf(String cnpjOuCpf) {
        this.cnpjOuCpf = cnpjOuCpf;
    }

    public PedidoDeCompra converterDtoParaModelo() {
        PedidoDeCompra pedidoDeCompra = new PedidoDeCompra();

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpjOuCpf(cnpjOuCpf);

        Responsavel responsavel = new Responsavel();
        responsavel.setEmail(this.emailResponsavel);

        pedidoDeCompra.setDataDeVencimento(this.dataDeVencimento);
        pedidoDeCompra.setValorAproximado(this.valorAproximado);
        pedidoDeCompra.setDataDePagamento(this.dataDePagamento);
        pedidoDeCompra.setResponsavel(responsavel);
        pedidoDeCompra.setDataLimiteEnvio(this.dataLimiteEnvio);
        pedidoDeCompra.setFormaDePagamento(this.formaDePagamento);
        pedidoDeCompra.setFornecedor(fornecedor);
        pedidoDeCompra.setCancelado(false);
        return pedidoDeCompra;
    }
}
