package br.com.zup.zupayments.exceptions.erros;

import br.com.zup.zupayments.exceptions.ExcecaoBasica;

public class ResponsavelNaoExisteException extends ExcecaoBasica {

    public ResponsavelNaoExisteException(){
        super("Não existe responsável com e-mail ",422,"e-mail","Not Found");
    }
}
