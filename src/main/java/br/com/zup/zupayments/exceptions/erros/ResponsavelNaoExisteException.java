package br.com.zup.zupayments.exceptions.erros;

import br.com.zup.zupayments.exceptions.ExcessaoBasica;

public class ResponsavelNaoExisteException extends ExcessaoBasica {

    public ResponsavelNaoExisteException(){
        super("Não existe responsável com e-mail ",422,"e-mail","Not Found");
    }
}
