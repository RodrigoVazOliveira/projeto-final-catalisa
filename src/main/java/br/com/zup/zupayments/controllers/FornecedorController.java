package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.fornecedor.entrada.CadastroDeFornecedorDTO;
import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.services.FornecedorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fornecedores/")
@Api(value = "API REST de fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cadastrar um novo fornecedor")
    public Fornecedor cadastrarFornecedor(@RequestBody CadastroDeFornecedorDTO cadastroDeFornecedorDTO){
        return fornecedorService.cadastrarFornecedor(
                cadastroDeFornecedorDTO.converterDtoParaModelo()
        );
    }

    @GetMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public Fornecedor pesquisarFornecedor(@PathVariable String cnpjouCpf){
        return fornecedorService.pesquisarFornecedorPorCnpjOuCpf(cnpjouCpf);
    }

    @PutMapping("{id}/")
    public Fornecedor atualizarFornecedor(@PathVariable String id, @RequestBody Fornecedor fornecedor){
        Fornecedor objFornecedor = fornecedorService.atualizarCadastroFornecedor(id, fornecedor);
        return objFornecedor;
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Desativa um fornecedor pelo CNPJ/CPF")
    public void ativarOuDesativarFornecedorPeloCnpjOuCpf(@RequestParam(name = "cnpjoucpf") String cnpjOuCpf) {
        fornecedorService.ativarOuDesativarFornecedor(cnpjOuCpf);
    }
}