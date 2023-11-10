package br.com.zup.zupayments.dtos.usuario.entrada;

import br.com.zup.zupayments.enums.RolesEnum;
import br.com.zup.zupayments.models.Usuario;
import jakarta.validation.constraints.*;

public class CadastrarUsuarioDTO {

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(min = 5, max = 50, message = "{validacao.tamanho_campo}")
    private String nomeCompleto;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(min = 5, max = 150, message = "{validacao.tamanho_campo}")
    @Email(message = "{validacao.email_invalido}")
    private String email;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    @NotBlank(message = "{validacao.campo_em_branco}")
    @NotEmpty(message = "{validacao.campo_vazio}")
    @Size(min = 6, max = 16, message = "{validacao.tamanho_campo}")
    private String senha;

    @NotNull(message = "{validacao.campo_obrigatorio}")
    private RolesEnum nivelDeAcesso;

    public CadastrarUsuarioDTO() {
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public RolesEnum getNivelDeAcesso() {
        return nivelDeAcesso;
    }

    public void setNivelDeAcesso(RolesEnum nivelDeAcesso) {
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public Usuario converterDtoParaModelo() {
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(this.nomeCompleto);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        usuario.setNivelDeAcesso(this.nivelDeAcesso);
        usuario.setAtivo(true);

        return usuario;
    }
}
