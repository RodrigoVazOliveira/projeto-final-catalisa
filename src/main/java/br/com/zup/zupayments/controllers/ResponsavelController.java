package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.responsavel.entrada.CadastrarResponsavelDTO;
import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.services.ResponsavelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("responsaveis/")
@Tag(name = "API REST de responsaveis")
public class ResponsavelController {

    private final ResponsavelService responsavelService;

    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "cadastrar um novo responsavel")
    Responsavel cadastrarResponsavel(@RequestBody @Valid CadastrarResponsavelDTO cadastrarResponsavelDTO){
        return responsavelService.cadastrarResponsavel(cadastrarResponsavelDTO.converterDtoParaModelo());
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Desativa um responsavel pelo email")
    void ativarOuDesativarResponsavel(@RequestParam(name = "email") String emailResponsavel) {
        responsavelService.ativarOuDesativarResponsavel(emailResponsavel);
    }
}
