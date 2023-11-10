package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.fornecedor.entrada.AtualizarFornecedorDTO;
import br.com.zup.zupayments.dtos.fornecedor.entrada.CadastroDeFornecedorDTO;
import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.services.FornecedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fornecedores/")
@Tag(name = "API REST de fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um novo fornecedor", description = "Cadastrar um novo fornecedor")
    Fornecedor cadastrarFornecedor(@RequestBody @Valid CadastroDeFornecedorDTO cadastroDeFornecedorDTO) {
        return fornecedorService.cadastrarFornecedor(cadastroDeFornecedorDTO.converterDtoParaModelo());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Fornecedor pesquisarFornecedor(@RequestParam(value = "cnpjOuCpf") String cnpjouCpf) {
        return fornecedorService.pesquisarFornecedorPorCnpjOuCpf(cnpjouCpf);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    Fornecedor atualizarFornecedor(@RequestBody AtualizarFornecedorDTO atualizarFornecedorDTO) {
        return fornecedorService.atualizarCadastroFornecedor(atualizarFornecedorDTO.converterDtoParaModelo());
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Desativa um fornecedor pelo CNPJ/CPF", description = "Desativa um fornecedor pelo CNPJ/CPF")
    public void ativarOuDesativarFornecedorPeloCnpjOuCpf(@RequestParam(name = "cnpjoucpf") String cnpjOuCpf) {
        fornecedorService.ativarOuDesativarFornecedor(cnpjOuCpf);
    }
}