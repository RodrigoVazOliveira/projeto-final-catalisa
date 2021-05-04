package br.com.zup.zupayments.dtos.fornecedor.entrada;

import br.com.zup.zupayments.enums.CategoriaDeCusto;
import br.com.zup.zupayments.models.Fornecedor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class CadastroDeFornecedorDTO {

    @CPF(message = "O CPF informado é inválido")
    private String cpf;

    @CNPJ(message = "O CNPJ informado é invalido")
    private String cnpj;

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

    public CadastroDeFornecedorDTO() {
    }

    public CadastroDeFornecedorDTO(String cpf, String cnpj, String razaoSocial, String logradouro,
                                   Integer numero, String bairro, String cidade, String estado,
                                   String cep, String telefone, String email, CategoriaDeCusto categoriaDeCusto) {
        this.cpf = cpf;
        this.cnpj = cnpj;
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
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public Fornecedor converterDtoParaModelo() {
        String cnpjOuCpf = null;

        if (cpf != null) {
            cnpjOuCpf = cpf;
        }

        if (cnpj != null) {
            cnpjOuCpf = cnpj;
        }

        return new Fornecedor(
                cnpjOuCpf,
                this.RazaoSocial,
                this.logradouro,
                this.numero,
                this.bairro,
                this.cidade,
                this.estado,
                this.cep,
                this.telefone,
                this.email,
                this.categoriaDeCusto,
                true
        );
    }
}
