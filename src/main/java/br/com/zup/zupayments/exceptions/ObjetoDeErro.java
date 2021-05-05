package br.com.zup.zupayments.exceptions;

public class ObjetoDeErro {
    private String mensagem;
    private String campo;

    public ObjetoDeErro() { }

    public ObjetoDeErro(String mensagem, String campo) {
        this.mensagem = mensagem;
        this.campo = campo;
    }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getCampo() { return campo; }
    public void setCampo(String campo) { this.campo = campo; }
}
