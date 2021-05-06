package br.com.zup.zupayments.exceptions.erros;

import br.com.zup.zupayments.exceptions.ExcecaoBasica;

public class ResponsavelJaCadastradoException extends RuntimeException {

    private Integer status = 422;
    private String tipoDeErro = "Responsável já cadastrado";
    private String motivo = "Unprocesable Entity";
    }
}
