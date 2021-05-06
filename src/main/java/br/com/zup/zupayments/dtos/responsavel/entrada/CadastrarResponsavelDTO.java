package br.com.zup.zupayments.dtos.responsavel.entrada;

import br.com.zup.zupayments.models.Responsavel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CadastrarResponsavelDTO {

    @Email(message = "{validacao.email_invalido}")
    private String email;

    @NotBlank(message = "{validacao.nome_completo}")
    private String nomeCompleto;
    private String nomeDoProjeto;

    public CadastrarResponsavelDTO() {
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeDoProjeto() {
        return nomeDoProjeto;
    }

    public void setNomeDoProjeto(String nomeDoProjeto) {
        this.nomeDoProjeto = nomeDoProjeto;
    }

    public Responsavel converterDtoParaModelo() {
        Responsavel responsavel = new Responsavel();
        responsavel.setNomeCompleto(this.nomeCompleto);
        responsavel.setNomeDoProjeto(this.nomeDoProjeto);
        responsavel.setEmail(this.email);
        responsavel.setAtivo(true);
        return responsavel;
    }
}
