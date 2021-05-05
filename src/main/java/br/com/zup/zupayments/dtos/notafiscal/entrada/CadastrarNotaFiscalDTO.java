package br.com.zup.zupayments.dtos.notafiscal.entrada;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class CadastrarNotaFiscalDTO {

    private Long numeroDaNota;
    private String cnpjOuCpfFornecedor;
    private Double valorAPagar;

    @JsonFormat(pattern = "dd/mm/YYYY")
    private LocalDate dataDeEmissao;
    private List<Long> pedidoDeCompra;

    @JsonFormat(pattern = "dd/mm/YYYY")
    private LocalDate dataDeEnvio;
    private String emailDoResponsavel;

    public CadastrarNotaFiscalDTO() {
    }

    public Long getNumeroDaNota() {
        return numeroDaNota;
    }

    public void setNumeroDaNota(Long numeroDaNota) {
        this.numeroDaNota = numeroDaNota;
    }

    public String getCnpjOuCpfFornecedor() {
        return cnpjOuCpfFornecedor;
    }

    public void setCnpjOuCpfFornecedor(String cnpjOuCpfFornecedor) {
        this.cnpjOuCpfFornecedor = cnpjOuCpfFornecedor;
    }

    public Double getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(Double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public LocalDate getDataDeEmissao() {
        return dataDeEmissao;
    }

    public void setDataDeEmissao(LocalDate dataDeEmissao) {
        this.dataDeEmissao = dataDeEmissao;
    }

    public List<Long> getPedidoDeCompra() {
        return pedidoDeCompra;
    }

    public void setPedidoDeCompra(List<Long> pedidoDeCompra) {
        this.pedidoDeCompra = pedidoDeCompra;
    }

    public LocalDate getDataDeEnvio() {
        return dataDeEnvio;
    }

    public void setDataDeEnvio(LocalDate dataDeEnvio) {
        this.dataDeEnvio = dataDeEnvio;
    }

    public String getEmailDoResponsavel() {
        return emailDoResponsavel;
    }

    public void setEmailDoResponsavel(String emailDoResponsavel) {
        this.emailDoResponsavel = emailDoResponsavel;
    }
}
