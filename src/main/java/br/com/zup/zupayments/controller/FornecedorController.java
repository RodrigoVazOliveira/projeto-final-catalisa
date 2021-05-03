package br.com.zup.zupayments.controller;

import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fornecedores/")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fornecedor cadastroFornecedor(@RequestBody Fornecedor fornecedor){
        return fornecedorService.cadastrarFornecedor(fornecedor);
    }

    @GetMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public Fornecedor pesquisaFornecedor(@PathVariable Integer id){
        return fornecedorService.pesquisarFornecedor(id);
    }

    @PutMapping("{id}/")
    public Fornecedor atualizarFornecedor(@PathVariable Integer id, @RequestBody Fornecedor fornecedor){
        Fornecedor objFornecedor = fornecedorService.atualizarCadastroFornecedor(id, fornecedor);
        return objFornecedor;
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFornecedor(@PathVariable Integer id){
        fornecedorService.deletarFornecedor(id);
    }
}