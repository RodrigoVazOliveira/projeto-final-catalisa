package br.com.zup.zupayments.models;

import br.com.zup.zupayments.enums.CategoriaDeCusto;

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

    private String telefone;

    private String email;

    private CategoriaDeCusto categoriaDeCusto;

    private Boolean ativo;

    public Fornecedor() {
    }

    public Fornecedor(String cnpjOuCpf, String razaoSocial, String logradouro, Integer numero, String bairro,
                      String cidade, String estado, String cep, String telefone, String email,
                      CategoriaDeCusto categoriaDeCusto, Boolean ativo) {
        CnpjOuCpf = cnpjOuCpf;
        RazaoSocial = razaoSocial;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
        this.categoriaDeCusto = categoriaDeCusto;
        this.ativo = ativo;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CategoriaDeCusto getCategoriaDeCusto() {
        return categoriaDeCusto;
    }

    public void setCategoriaDeCusto(CategoriaDeCusto categoriaDeCusto) {
        this.categoriaDeCusto = categoriaDeCusto;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
