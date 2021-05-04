package br.com.zup.zupayments.dtos.responsavel.entrada;

import br.com.zup.zupayments.models.Responsavel;

public class CadastrarResponsavelDTO {

    private String email;
    private String nomeCompleto;
    private String nomeDoProjeto;

    public CadastrarResponsavelDTO() {
    }

    public CadastrarResponsavelDTO(String email, String nomeCompleto, String nomeDoProjeto) {
        this.email = email;
        this.nomeCompleto = nomeCompleto;
        this.nomeDoProjeto = nomeDoProjeto;
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
        return new Responsavel(
                this.email,
                this.nomeCompleto,
                this.nomeDoProjeto,
                true
        );
    }
}
