package br.com.zup.zupayments.dtos.responsavel.entrada;

import br.com.zup.zupayments.models.Responsavel;

public class CadastrarResponsavelDTO {

    private String nomeCompleto;
    private String nomeDoProjeto;

    public CadastrarResponsavelDTO() {
    }

    public CadastrarResponsavelDTO(String nomeCompleto, String nomeDoProjeto) {
        this.nomeCompleto = nomeCompleto;
        this.nomeDoProjeto = nomeDoProjeto;
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
                null,
                this.nomeCompleto,
                this.nomeDoProjeto,
                true
        );
    }
}
