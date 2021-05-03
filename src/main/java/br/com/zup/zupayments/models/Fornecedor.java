package br.com.zup.zupayments.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {

    @Id
    private String CnpjOuCpf;

    private String RazaoSocial;

    private String logradouro;

    private Integer numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;

    private CategoriaDeCusto categoriaDeCusto;

    public Fornecedor() {
    }

    public Fornecedor(String cnpjOuCpf, String razaoSocial, String logradouro,
                      Integer numero, String bairro, String cidade,
                      String estado, String cep, CategoriaDeCusto categoriaDeCusto) {
        CnpjOuCpf = cnpjOuCpf;
        RazaoSocial = razaoSocial;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.categoriaDeCusto = categoriaDeCusto;
    }

    public String getCnpjOuCpf() {
        return CnpjOuCpf;
    }

    public void setCnpjOuCpf(String cnpjOuCpf) {
        CnpjOuCpf = cnpjOuCpf;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        RazaoSocial = razaoSocial;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public CategoriaDeCusto getCategoriaDeCusto() {
        return categoriaDeCusto;
    }

    public void setCategoriaDeCusto(CategoriaDeCusto categoriaDeCusto) {
        this.categoriaDeCusto = categoriaDeCusto;
    }
}
