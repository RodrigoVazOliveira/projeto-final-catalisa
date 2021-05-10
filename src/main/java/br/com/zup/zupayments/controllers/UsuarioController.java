package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.usuario.entrada.CadastrarUsuarioDTO;
import br.com.zup.zupayments.dtos.usuario.entrada.NivelDeAcessoUsuarioDTO;
import br.com.zup.zupayments.dtos.usuario.saida.UsuarioDTO;
import br.com.zup.zupayments.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("usuarios/")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarNovoUsuario(@RequestBody @Valid CadastrarUsuarioDTO usuario) {
        usuarioService.cadastrarNovoUsuario(usuario.converterDtoParaModelo());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<UsuarioDTO> mostarTodosUsuarios() {
        return UsuarioDTO.converterListaDeModeloParaListaDto(usuarioService.obterTodosUsuarios());
    }

    @PatchMapping("ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarOuDesativarUsuario(@RequestParam(name = "idUsuario") Long id) {
        usuarioService.ativarOuDesativarUsuario(id);
    }

    @PatchMapping("nivel")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO atualizarNivelDeAcesso(@RequestParam(name = "idUsuario") Long id,
                                             @RequestBody NivelDeAcessoUsuarioDTO nivelDeAcessoUsuarioDTO) {
        return UsuarioDTO.converterModeloParaDTO(
                usuarioService.atualizarNivelDeAcesso(id, nivelDeAcessoUsuarioDTO.getNivelDeAcesso())
        );
    }
}
