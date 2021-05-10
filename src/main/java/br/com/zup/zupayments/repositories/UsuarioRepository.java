package br.com.zup.zupayments.repositories;

import br.com.zup.zupayments.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
