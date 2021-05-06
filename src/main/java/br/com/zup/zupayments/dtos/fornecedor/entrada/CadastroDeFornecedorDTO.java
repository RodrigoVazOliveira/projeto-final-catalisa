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

    private String razaoSocial;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;

    private String telefone;

    private String email;

    private CategoriaDeCusto categoriaDeCusto;

    public CadastroDeFornecedorDTO() {
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
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
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

        if (this.cpf != null) {
            cnpjOuCpf = cpf;
        }

        if (this.cnpj != null) {
            cnpjOuCpf = cnpj;
        }

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpjOuCpf(cnpjOuCpf);
        fornecedor.setRazaoSocial(this.razaoSocial);
        fornecedor.setLogradouro(this.logradouro);
        fornecedor.setNumero(this.numero);
        fornecedor.setBairro(this.bairro);
        fornecedor.setCidade(this.cidade);
        fornecedor.setEstado(this.estado);
        fornecedor.setCep(this.cep);
        fornecedor.setTelefone(this.telefone);
        fornecedor.setEmail(this.email);
        fornecedor.setCategoriaDeCusto(this.categoriaDeCusto);
        fornecedor.setAtivo(true);

        return fornecedor;
    }
}
