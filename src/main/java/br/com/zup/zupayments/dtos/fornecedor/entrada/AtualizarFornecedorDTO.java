package br.com.zup.zupayments.dtos.fornecedor.entrada;

import br.com.zup.zupayments.enums.CategoriaDeCusto;
import br.com.zup.zupayments.models.Fornecedor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

public class AtualizarFornecedorDTO {

    @CPF(message = "{validacao.cpf_invalido}")
    private String cpf;

    @CNPJ(message = "{validacao.cnpj_invalido}")
    private String cnpj;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(max = 100, message = "{validacao.tamanho_campo}")
    private String razaoSocial;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(max = 80, message = "{validacao.tamanho_campo}")
    private String logradouro;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(max = 10, message = "{validacao.tamanho_campo}")
    private String numero;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(max = 60, message = "{validacao.tamanho_campo}")
    private String bairro;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(max = 80, message = "{validacao.tamanho_campo}")
    private String cidade;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(max = 25, message = "{validacao.tamanho_campo}")
    private String estado;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(max = 15, message = "{validacao.tamanho_campo}")
    private String cep;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(max = 25, message = "{validacao.tamanho_campo}")
    private String telefone;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(max = 80, message = "{validacao.tamanho_campo}")
    @Email(message = "{validacao.email_invalido}")
    private String email;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    private CategoriaDeCusto categoriaDeCusto;

    public AtualizarFornecedorDTO() {
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

        return fornecedor;
    }
}
