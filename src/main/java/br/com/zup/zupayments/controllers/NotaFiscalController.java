package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.notafiscal.entrada.CadastrarNotaFiscalDTO;
import br.com.zup.zupayments.models.NotaFiscal;
import br.com.zup.zupayments.services.NotaFiscalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notas_fiscais/")
@Api(value = "API REST de Notas fiscais")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService notaFiscalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cadastrar uma nova nota fiscal")
    public NotaFiscal cadastroNotaFiscal(@RequestBody CadastrarNotaFiscalDTO cadastrarNotaFiscalDTO){
        return notaFiscalService.cadastrarNotaFiscal(cadastrarNotaFiscalDTO.converterDtoParaModelo());
    }

    @PatchMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Cancelar uma nota fiscal pelo id")
    public NotaFiscal cancelamentoDeNotaFiscal(@PathVariable Long id){
        NotaFiscal objNotaFiscal = notaFiscalService.cancelarNF(id);
        return objNotaFiscal;
    }
}
