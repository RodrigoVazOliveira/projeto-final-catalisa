package br.com.zup.zupayments.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "notas_fiscais")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long numeroDaNota;

    @ManyToOne
    private Fornecedor fornecedor;

    private Double valorAPagar;

    private LocalDate dataDeEmissao;

    @ManyToMany
    private List<PedidoDeCompra> pedidoDeCompra;

    private LocalDate dataDeEnvio;

    @ManyToOne
    private Responsavel responsavel;

    private Boolean cancelar;

    public NotaFiscal() {
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

    public List<PedidoDeCompra> getPedidoDeCompra() {
        return pedidoDeCompra;
    }

    public void setPedidoDeCompra(List<PedidoDeCompra> pedidoDeCompra) {
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

    public Boolean getCancelar() {
        return cancelar;
    }

    public void setCancelar(Boolean cancelar) {
        this.cancelar = cancelar;
    }
}
