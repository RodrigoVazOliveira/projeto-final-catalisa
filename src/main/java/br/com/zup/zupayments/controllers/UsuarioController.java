package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.usuario.entrada.CadastrarUsuarioDTO;
import br.com.zup.zupayments.dtos.usuario.entrada.NivelDeAcessoUsuarioDTO;
import br.com.zup.zupayments.dtos.usuario.saida.UsuarioDTO;
import br.com.zup.zupayments.models.Usuario;
import br.com.zup.zupayments.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios/")
@Tag(name = "API REST de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um novo usuário")
    void cadastrarNovoUsuario(@RequestBody @Valid CadastrarUsuarioDTO usuario) {
        usuarioService.cadastrarNovoUsuario(usuario.converterDtoParaModelo());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Mostrar todos os usuários")
    Iterable<UsuarioDTO> mostarTodosUsuarios() {
        return UsuarioDTO.converterListaDeModeloParaListaDto(usuarioService.obterTodosUsuarios());
    }

    @PatchMapping("ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Ativar ou desativar um usuário pelo id")
    void ativarOuDesativarUsuario(@RequestParam(name = "idUsuario") Long id) {
        usuarioService.ativarOuDesativarUsuario(id);
    }

    @PatchMapping("nivel")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Alterar o nível de acesso do usuário")
    UsuarioDTO atualizarNivelDeAcesso(@RequestParam(name = "idUsuario") Long id, @RequestBody NivelDeAcessoUsuarioDTO nivelDeAcessoUsuarioDTO) {
        Usuario usuario = usuarioService.atualizarNivelDeAcesso(id, nivelDeAcessoUsuarioDTO.getNivelDeAcesso());

        return UsuarioDTO.converterModeloParaDTO(usuario);
    }
}
