package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.responsavel.entrada.CadastrarResponsavelDTO;
import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.services.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("responsaveis/")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Responsavel cadastrarResponsavel(@RequestBody CadastrarResponsavelDTO cadastrarResponsavelDTO){
        return responsavelService.cadastrarResponsavel(
                cadastrarResponsavelDTO.converterDtoParaModelo()
        );
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarOuDesativarResponsavel(@RequestParam(name = "email") String emailResponsavel) {
        responsavelService.ativarOuDesativarResponsavel(emailResponsavel);
    }
}
