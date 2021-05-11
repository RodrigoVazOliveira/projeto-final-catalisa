package br.com.zup.zupayments.jwt;

import br.com.zup.zupayments.enums.RolesEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class    giUsuarioLogin implements UserDetails {

    private String email;
    private String senha;
    private String nomeCompleto;
    private RolesEnum rolesEnum;

    public UsuarioLogin(String email, String senha, String nomeCompleto, RolesEnum rolesEnum) {
        this.email = email;
        this.senha = senha;
        this.nomeCompleto = nomeCompleto;
        this.rolesEnum = rolesEnum;
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

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public RolesEnum getRolesEnum() {
        return rolesEnum;
    }

    public void setRolesEnum(RolesEnum rolesEnum) {
        this.rolesEnum = rolesEnum;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(
                new SimpleGrantedAuthority(String.valueOf(this.rolesEnum))
        );
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}