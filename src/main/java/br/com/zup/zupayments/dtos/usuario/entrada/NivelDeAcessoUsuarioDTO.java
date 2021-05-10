package br.com.zup.zupayments.dtos.usuario.entrada;

import br.com.zup.zupayments.enums.RolesEnum;

public class NivelDeAcessoUsuarioDTO {
    private RolesEnum nivelDeAcesso;

    public NivelDeAcessoUsuarioDTO() {
    }

    public RolesEnum getNivelDeAcesso() {
        return nivelDeAcesso;
    }

    public void setNivelDeAcesso(RolesEnum nivelDeAcesso) {
        this.nivelDeAcesso = nivelDeAcesso;
    }
}
