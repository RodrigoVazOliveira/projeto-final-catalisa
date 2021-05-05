package br.com.zup.zupayments.exceptions;

import java.util.List;

public class RespostaDeErro {
    private String tipoErro;
    private int codigo;
    private String status;
    private List<ObjetoDeErro> objetosDeErro;

    public RespostaDeErro(String tipoErro, int codigo, String status, List<ObjetoDeErro> objetosDeErro) {
        this.tipoErro = tipoErro;
        this.codigo = codigo;
        this.status = status;
        this.objetosDeErro = objetosDeErro;
    }

    public RespostaDeErro() { }

    public String getTipoErro() { return tipoErro; }
    public void setTipoErro(String tipoErro) { this.tipoErro = tipoErro; }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<ObjetoDeErro> getObjetosDeErro() { return objetosDeErro; }
    public void setObjetosDeErro(List<ObjetoDeErro> objetosDeErro) {
        this.objetosDeErro = objetosDeErro;
    }
}
