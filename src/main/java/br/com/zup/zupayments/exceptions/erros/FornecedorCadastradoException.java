package br.com.zup.zupayments.exceptions.erros;

public class FornecedorCadastradoException extends RuntimeException{

    private Integer status = 400;
    private String tipoDeErro = "Fornecedor já cadastrado";
    private String motivo = "BAD REQUEST";

    public FornecedorCadastradoException(String message) {
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