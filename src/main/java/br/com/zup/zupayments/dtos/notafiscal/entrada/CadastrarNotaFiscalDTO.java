package br.com.zup.zupayments.dtos.notafiscal.entrada;

import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.models.NotaFiscal;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.models.Responsavel;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CadastrarNotaFiscalDTO {

    @NotNull(message = "{validacao.campo_obrigatorio}")
    private Long numeroDaNota;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    private String cnpjOuCpfFornecedor;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    private Double valorAPagar;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeEmissao;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    private List<Long> pedidoDeCompras;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeEnvio;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Email(message = "{validacao.email_invalido}")
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

    public List<Long> getPedidoDeCompras() {
        return pedidoDeCompras;
    }

    public void setPedidoDeCompras(List<Long> pedidoDeCompras) {
        this.pedidoDeCompras = pedidoDeCompras;
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

    public NotaFiscal converterDtoParaModelo() {
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setNumeroDaNota(this.numeroDaNota);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpjOuCpf(this.cnpjOuCpfFornecedor);

        notaFiscal.setFornecedor(fornecedor);
        notaFiscal.setValorAPagar(this.valorAPagar);
        notaFiscal.setDataDeEmissao(this.dataDeEmissao);
        notaFiscal.setPedidoDeCompra(gerarListaDePedidoDeCompraParaModelo());
        notaFiscal.setDataDeEnvio(this.dataDeEnvio);

        Responsavel responsavel = new Responsavel();
        responsavel.setEmail(this.emailDoResponsavel);
        notaFiscal.setResponsavel(responsavel);
        notaFiscal.setCancelar(false);

        return notaFiscal;
    }

    private List<PedidoDeCompra> gerarListaDePedidoDeCompraParaModelo() {
        List<PedidoDeCompra> pedidos = new ArrayList<>();

        for (Long numeroPedido : this.pedidoDeCompras) {
            PedidoDeCompra pedido = new PedidoDeCompra();
            pedido.setNumeroDePedido(numeroPedido);
            pedidos.add(pedido);
        }

        return pedidos;
    }
}
