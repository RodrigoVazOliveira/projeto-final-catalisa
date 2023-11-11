package br.com.zup.zupayments.repositories;

import br.com.zup.zupayments.models.Responsavel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends CrudRepository<Responsavel,String> {
    Boolean existsByEmail(String email);
}
