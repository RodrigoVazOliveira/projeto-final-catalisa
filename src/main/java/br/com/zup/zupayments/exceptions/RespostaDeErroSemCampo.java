package br.com.zup.zupayments.exceptions;

import java.util.List;

public class RespostaDeErroSemCampo {
    private String tipoErro;
    private int codigo;
    private String status;
    private String mensagem;

    public RespostaDeErroSemCampo() {
    }

    public RespostaDeErroSemCampo(String tipoErro, int codigo, String status, String mensagem) {
        this.tipoErro = tipoErro;
        this.codigo = codigo;
        this.status = status;
        this.mensagem = mensagem;
    }

    public String getTipoErro() {
        return tipoErro;
    }

    public void setTipoErro(String tipoErro) {
        this.tipoErro = tipoErro;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
