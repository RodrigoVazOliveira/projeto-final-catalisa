package br.com.zup.zupayments.exceptions.erros;

import br.com.zup.zupayments.exceptions.ExcecaoBasica;



public class ResponsavelJaCadastradoException extends ExcecaoBasica {

    public ResponsavelJaCadastradoException(){
        super("Responsável já cadastrado",422,"e-mail","Unprocesable Entity");
    }
}
