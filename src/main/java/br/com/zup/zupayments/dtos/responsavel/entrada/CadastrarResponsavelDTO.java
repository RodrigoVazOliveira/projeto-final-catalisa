package br.com.zup.zupayments.dtos.responsavel.entrada;

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
}
