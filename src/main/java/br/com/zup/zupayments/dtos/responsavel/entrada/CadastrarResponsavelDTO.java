package br.com.zup.zupayments.dtos.responsavel.entrada;

import br.com.zup.zupayments.models.Responsavel;

public class CadastrarResponsavelDTO {

    private String email;
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
        responsavel.setEmail(this.email);
        responsavel.setAtivo(true);
        return responsavel;
    }
}
