package br.com.zup.zupayments.services;

import br.com.zup.zupayments.enums.RolesEnum;
import br.com.zup.zupayments.exceptions.erros.UsuarioJaExisteComEmailException;
import br.com.zup.zupayments.exceptions.erros.UsuarioNaoExisteException;
import br.com.zup.zupayments.models.Usuario;
import br.com.zup.zupayments.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Usuario cadastrarNovoUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new UsuarioJaExisteComEmailException("Usuário com e-mail " + usuario.getEmail() + " já cadastrado!");
        }

        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Iterable<Usuario> obterTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario procurarUsuarioPeloId(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isEmpty()) {
            throw new UsuarioNaoExisteException("O usuário com id " + id + " não existe!");
        }

        return optionalUsuario.get();
    }

    public void ativarOuDesativarUsuario(Long id) {
        Usuario usuarioAtual = procurarUsuarioPeloId(id);
        usuarioAtual.setAtivo(!usuarioAtual.getAtivo());
        usuarioRepository.save(usuarioAtual);
    }

    public Usuario atualizarNivelDeAcesso(Long id, RolesEnum nivelDeAcesso) {
        Usuario usuario = procurarUsuarioPeloId(id);
        usuario.setNivelDeAcesso(nivelDeAcesso);
        return usuarioRepository.save(usuario);
    }

    public Usuario procurarUsuarioPeloEmail(String email) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isEmpty()) {
            throw new RuntimeException("Usuário com email " + email + " não localizado!");
        }

        return optionalUsuario.get();
    }
}
