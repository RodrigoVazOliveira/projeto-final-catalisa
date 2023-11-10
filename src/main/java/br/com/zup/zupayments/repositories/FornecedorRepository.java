package br.com.zup.zupayments.repositories;

import br.com.zup.zupayments.models.Fornecedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends CrudRepository<Fornecedor, String> {
    Boolean existsByCnpjOuCpf(String cnpjOuCpf);
}