package br.com.zup.zupayments.exceptions;

public class ExcessaoBasica extends RuntimeException{
    private int status;
    private String campo;
    private String motivo;

    public ExcessaoBasica(String message, int status, String campo, String motivo) {
        super(message);
        this.status = status;
        this.campo = campo;
        this.motivo = motivo;
    }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getCampo() { return campo; }
    public void setCampo(String campo) { this.campo = campo; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}
