package br.com.zup.zupayments.dtos.pedidodecompras.entrada;

import br.com.zup.zupayments.enums.FormaDePagamento;
import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.models.Responsavel;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import java.time.LocalDate;

public class EntradaCadastroPedidoDeCompraDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeVencimento;
    private Double saldo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDePagamento;

    @Email(message = "{validacao.email_invalido}")
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
        pedidoDeCompra.setSaldo(this.saldo);
        pedidoDeCompra.setDataDePagamento(this.dataDePagamento);
        pedidoDeCompra.setResponsavel(responsavel);
        pedidoDeCompra.setDataLimiteEnvio(this.dataLimiteEnvio);
        pedidoDeCompra.setFormaDePagamento(this.formaDePagamento);
        pedidoDeCompra.setFornecedor(fornecedor);
        pedidoDeCompra.setCancelado(false);
        return pedidoDeCompra;
    }
}
