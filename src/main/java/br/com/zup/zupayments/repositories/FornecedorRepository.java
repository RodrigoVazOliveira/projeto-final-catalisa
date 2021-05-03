package br.com.zup.zupayments.repositories;

import br.com.zup.zupayments.models.Fornecedor;
import org.springframework.data.repository.CrudRepository;

public interface FornecedorRepository extends CrudRepository<Fornecedor, String> {
}
