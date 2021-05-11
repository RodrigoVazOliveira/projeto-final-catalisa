package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.responsavel.entrada.CadastrarResponsavelDTO;
import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.repositories.ResponsavelRepository;
import br.com.zup.zupayments.services.ResponsavelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("responsaveis/")
@Api(value = "API REST de responsaveis")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "cadastrar um novo responsavel")
    public Responsavel cadastrarResponsavel(@RequestBody @Valid CadastrarResponsavelDTO cadastrarResponsavelDTO){
        return responsavelService.cadastrarResponsavel(
                cadastrarResponsavelDTO.converterDtoParaModelo()
        );
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Desativa um responsavel pelo email")
    public void ativarOuDesativarResponsavel(@RequestParam(name = "email") String emailResponsavel) {
        responsavelService.ativarOuDesativarResponsavel(emailResponsavel);
    }
}
