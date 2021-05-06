package br.com.zup.zupayments.exceptions.erros;

import br.com.zup.zupayments.exceptions.ExcessaoBasica;

public class ResponsavelJaCadastradoException extends ExcessaoBasica {

    public ResponsavelJaCadastradoException(){
        super("Responsável já cadastrado",422,"e-mail","Unprocesable Entity");
    }
}
