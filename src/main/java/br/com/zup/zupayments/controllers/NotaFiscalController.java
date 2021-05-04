package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.models.NotaFiscal;
import br.com.zup.zupayments.services.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notas_fiscais/")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService notaFiscalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotaFiscal cadastroNotaFiscal(@RequestBody NotaFiscal fiscal){
        return notaFiscalService.cadastrarNotaFiscal(fiscal);
    }
}
