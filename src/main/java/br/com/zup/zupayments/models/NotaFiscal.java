package br.com.zup.zupayments.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notas_fiscais")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numeroDaNota;

    @ManyToOne
    private Fornecedor fornecedor;

    private Double valorAPagar;

    private LocalDate dataDeEmissao;

    @ManyToMany
    private PedidoDeCompra pedidoDeCompra;

    private LocalDate dataDeEnvio;

    @ManyToOne
    private Responsavel responsavel;

    public NotaFiscal() {
    }

    public NotaFiscal(Long id, Long numeroDaNota, Fornecedor fornecedor, Double valorAPagar,
                      LocalDate dataDeEmissao, PedidoDeCompra pedidoDeCompra, LocalDate dataDeEnvio,
                      Responsavel responsavel) {
        this.id = id;
        this.numeroDaNota = numeroDaNota;
        this.fornecedor = fornecedor;
        this.valorAPagar = valorAPagar;
        this.dataDeEmissao = dataDeEmissao;
        this.pedidoDeCompra = pedidoDeCompra;
        this.dataDeEnvio = dataDeEnvio;
        this.responsavel = responsavel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroDaNota() {
        return numeroDaNota;
    }

    public void setNumeroDaNota(Long numeroDaNota) {
        this.numeroDaNota = numeroDaNota;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
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

    public PedidoDeCompra getPedidoDeCompra() {
        return pedidoDeCompra;
    }

    public void setPedidoDeCompra(PedidoDeCompra pedidoDeCompra) {
        this.pedidoDeCompra = pedidoDeCompra;
    }

    public LocalDate getDataDeEnvio() {
        return dataDeEnvio;
    }

    public void setDataDeEnvio(LocalDate dataDeEnvio) {
        this.dataDeEnvio = dataDeEnvio;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
}
