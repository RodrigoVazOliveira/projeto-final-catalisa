package br.com.zup.zupayments.services;

import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor cadastrarFornecedor (Fornecedor fornecedor) {
        try {
            return fornecedorRepository.save(fornecedor);
        } catch (Exception error) {
            throw new RuntimeException("Fornecedor já cadastrado");
        }
    }

    public Fornecedor pesquisarFornecedorPorCnpjOuCpf(String cnpjOuCpf){
        Optional<Fornecedor> optionalFornecedor = fornecedorRepository.findById(cnpjOuCpf);

        if (optionalFornecedor.isPresent()){
            return optionalFornecedor.get();
        }
            throw new RuntimeException("Fornecedor não foi encontrado");
    }

    public Fornecedor atualizarCadastroFornecedor (String id, Fornecedor fornecedor){

        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);

        if (!fornecedorOptional.isPresent())
            throw new RuntimeException("Fornecedor não foi encontrado para atualização");

        fornecedor.setCnpjOuCpf(id);
        fornecedorRepository.save(fornecedor);

        return fornecedor;
    }

    public void deletarFornecedor(String id) {
        try {
            fornecedorRepository.deleteById(id);
        } catch (Exception error) {
            throw new RuntimeException("Fornecedor não encontrado");
        }
    }
}