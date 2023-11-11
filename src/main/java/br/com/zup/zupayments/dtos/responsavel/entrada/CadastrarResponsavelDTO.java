package br.com.zup.zupayments.dtos.responsavel.entrada;

import br.com.zup.zupayments.models.Responsavel;
import jakarta.validation.constraints.*;

public class CadastrarResponsavelDTO {

    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(max = 255, message = "{validacao.tamanho_campo}")
    @Email(message = "{validacao.email_invalido}")
    private String email;

    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(max = 100, message = "{validacao.tamanho_campo}")
    private String nomeCompleto;

    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(max = 70, message = "{validacao.tamanho_campo}")
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
