package br.com.zup.zupayments.services;

import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.repositories.FornecedorRepository;
import br.com.zup.zupayments.repositories.PedidoDeCompraRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor cadastrarFornecedor (Fornecedor fornecedor){
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor pesquisarFornecedor (String id){
        Optional<Fornecedor> optionalFornecedor = fornecedorRepository.findById(id);

        if (optionalFornecedor.isPresent()){
            return optionalFornecedor.get();
        }
        throw new RuntimeException("Fornecedor não foi encontrado");
    }

    public Fornecedor atualizarCadastroFornecedor (String id, Fornecedor fornecedor){

        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);

        if (!fornecedorOptional.isPresent())
            throw new RuntimeException("Fornecedor não foi encontrado para atualização");

        fornecedor.setCnpjOuCpf(fornecedor.getCnpjOuCpf());

        return fornecedor;
    }

    public void deletarFornecedor(String id){
         fornecedorRepository.deleteById(id);
    }
}