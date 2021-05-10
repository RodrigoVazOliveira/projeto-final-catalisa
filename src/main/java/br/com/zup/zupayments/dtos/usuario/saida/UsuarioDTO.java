package br.com.zup.zupayments.dtos.usuario.saida;

import br.com.zup.zupayments.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {

    private String email;
    private String nomeCompleto;

    public UsuarioDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public static UsuarioDTO converterModeloParaDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNomeCompleto(usuario.getNomeCompleto());
        dto.setEmail(dto.getEmail());

        return dto;
    }

    public static Iterable<UsuarioDTO> converterListaDeModeloParaListaDto(Iterable<Usuario> usuarios) {
        List<UsuarioDTO> dtos = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            dtos.add(converterModeloParaDTO(usuario));
        }

        return dtos;
    }
}
