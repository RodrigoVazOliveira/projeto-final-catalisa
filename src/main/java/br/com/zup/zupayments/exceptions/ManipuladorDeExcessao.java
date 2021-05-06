package br.com.zup.zupayments.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManipuladorDeExcessao extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<ObjetoDeErro> objetosDeErro = criarObjetoDeErro(ex);
        RespostaDeErro respostaDeErro = new RespostaDeErro("Validação", status.value(),
                status.getReasonPhrase(), objetosDeErro);

        return ResponseEntity.status(status).body(respostaDeErro);
    }

    private List<ObjetoDeErro> criarObjetoDeErro(MethodArgumentNotValidException exception) {
        List<ObjetoDeErro> objetosDeErro = new ArrayList<>();
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        for (FieldError listaDeErros : errors) {
            ObjetoDeErro objetoDeErro = new ObjetoDeErro(listaDeErros.getDefaultMessage(),
                    listaDeErros.getField());
            objetosDeErro.add(objetoDeErro);
        }

        return objetosDeErro;
    }

    @ExceptionHandler({ExcessaoBasica.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RespostaDeErro manipularRuntimeException(ExcessaoBasica erro){
        ObjetoDeErro objetoDeErro = new ObjetoDeErro(erro.getMessage(), erro.getCampo());
        RespostaDeErro respostaDeErro = new RespostaDeErro(erro.getMessage(), erro.getStatus(),
                erro.getMotivo(),   Arrays.asList(objetoDeErro));

        return respostaDeErro;
    }

}
