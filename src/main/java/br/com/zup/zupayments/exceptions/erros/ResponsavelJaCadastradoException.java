package br.com.zup.zupayments.exceptions.erros;

public class ResponsavelJaCadastradoException extends RuntimeException {
    private Integer status = 422;
    private String tipoDeErro = "Responsável já cadastrado";
    private String motivo = "Unprocesable Entity";

    public ResponsavelJaCadastradoException(String message) {
        super(message);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTipoDeErro() {
        return tipoDeErro;
    }

    public void setTipoDeErro(String tipoDeErro) {
        this.tipoDeErro = tipoDeErro;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
