package br.com.zup.zupayments.jwt;

import br.com.zup.zupayments.exceptions.TokenNotValidException;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltroDeAutorizacao extends BasicAuthenticationFilter {

    private ComponenteJWT componenteJWT;
    private UsuarioLoginService userDetailsService;

    public FiltroDeAutorizacao(AuthenticationManager authenticationManager, ComponenteJWT componenteJWT,
                               UsuarioLoginService usuarioLoginService) {
        super(authenticationManager);
        this.componenteJWT = componenteJWT;
        this.userDetailsService = usuarioLoginService;
    }

    private UsernamePasswordAuthenticationToken pegarAutenticacao(HttpServletRequest request, String token){
        if (!componenteJWT.isTokenValid(token)){
            throw new TokenNotValidException("If quebrou");
        }
        Claims claims = componenteJWT.getClaims(token);
        UserDetails usuario = userDetailsService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String autorizacao = request.getHeader("Authorization");

        if (autorizacao != null && autorizacao.startsWith("Token ")){// substring é o número de caracteres dentro do ()
            try {
                UsernamePasswordAuthenticationToken auth = pegarAutenticacao(request, autorizacao.substring(6));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }catch (TokenNotValidException error){
                error.getStackTrace();
            }
        }
        chain.doFilter(request, response);
    }
}