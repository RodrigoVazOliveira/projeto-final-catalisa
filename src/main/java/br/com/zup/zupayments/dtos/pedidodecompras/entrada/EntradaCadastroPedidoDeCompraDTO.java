package br.com.zup.zupayments.dtos.pedidodecompras.entrada;

import br.com.zup.zupayments.models.FormaDePagamento;

import java.time.LocalDate;

public class EntradaCadastroPedidoDeCompraDTO {

    private LocalDate dataDeVencimento;
    private Double valorAproximado;
    private LocalDate dataDePagamento;
    private String emailResponsavel;
    private LocalDate dataLimiteEnvio;
    private FormaDePagamento formaDePagamento;
    private String CnpjOuCpf;

    public EntradaCadastroPedidoDeCompraDTO() {
    }

    public EntradaCadastroPedidoDeCompraDTO(LocalDate dataDeVencimento, Double valorAproximado,
                                            LocalDate dataDePagamento, String emailResponsavel,
                                            LocalDate dataLimiteEnvio, FormaDePagamento formaDePagamento,
                                            String cnpjOuCpf) {
        this.dataDeVencimento = dataDeVencimento;
        this.valorAproximado = valorAproximado;
        this.dataDePagamento = dataDePagamento;
        this.emailResponsavel = emailResponsavel;
        this.dataLimiteEnvio = dataLimiteEnvio;
        this.formaDePagamento = formaDePagamento;
        CnpjOuCpf = cnpjOuCpf;
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
        return CnpjOuCpf;
    }

    public void setCnpjOuCpf(String cnpjOuCpf) {
        CnpjOuCpf = cnpjOuCpf;
    }
}
