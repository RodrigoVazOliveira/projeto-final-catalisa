package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.notafiscal.entrada.CadastrarNotaFiscalDTO;
import br.com.zup.zupayments.models.NotaFiscal;
import br.com.zup.zupayments.services.NotaFiscalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notas_fiscais/")
@Tag(name = "API REST de Notas fiscais")
public class NotaFiscalController {
    private final NotaFiscalService notaFiscalService;

    public NotaFiscalController(NotaFiscalService notaFiscalService) {
        this.notaFiscalService = notaFiscalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar uma nova nota fiscal", description = "Cadastrar uma nova nota fiscal")
    NotaFiscal cadastroNotaFiscal(@RequestBody @Valid CadastrarNotaFiscalDTO cadastrarNotaFiscalDTO){
        return notaFiscalService.cadastrarNotaFiscal(cadastrarNotaFiscalDTO.converterDtoParaModelo());
    }

    @PatchMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cancelar uma nota fiscal pelo id", description = "Cancelar uma nota fiscal pelo id")
    NotaFiscal cancelamentoDeNotaFiscal(@PathVariable Long id){
        return notaFiscalService.cancelarNF(id);
    }
}
