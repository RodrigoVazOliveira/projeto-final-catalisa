package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.usuario.entrada.CadastrarUsuarioDTO;
import br.com.zup.zupayments.dtos.usuario.entrada.NivelDeAcessoUsuarioDTO;
import br.com.zup.zupayments.dtos.usuario.saida.UsuarioDTO;
import br.com.zup.zupayments.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("usuarios/")
@Api(value = "API REST de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cadastrar um novo usuário")
    public void cadastrarNovoUsuario(@RequestBody @Valid CadastrarUsuarioDTO usuario) {
        usuarioService.cadastrarNovoUsuario(usuario.converterDtoParaModelo());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Mostrar todos os usuários")
    public Iterable<UsuarioDTO> mostarTodosUsuarios() {
        return UsuarioDTO.converterListaDeModeloParaListaDto(usuarioService.obterTodosUsuarios());
    }

    @PatchMapping("ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Ativar ou desativar um usuário pelo id")
    public void ativarOuDesativarUsuario(@RequestParam(name = "idUsuario") Long id) {
        usuarioService.ativarOuDesativarUsuario(id);
    }

    @PatchMapping("nivel")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Alterar o nível de acesso do usuário")
    public UsuarioDTO atualizarNivelDeAcesso(@RequestParam(name = "idUsuario") Long id,
                                             @RequestBody NivelDeAcessoUsuarioDTO nivelDeAcessoUsuarioDTO) {
        return UsuarioDTO.converterModeloParaDTO(
                usuarioService.atualizarNivelDeAcesso(id, nivelDeAcessoUsuarioDTO.getNivelDeAcesso())
        );
    }
}
