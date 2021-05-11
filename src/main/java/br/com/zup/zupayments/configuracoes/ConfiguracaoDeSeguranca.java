package br.com.zup.zupayments.configuracoes;

import br.com.zup.zupayments.enums.RolesEnum;
import br.com.zup.zupayments.jwt.ComponenteJWT;
import br.com.zup.zupayments.jwt.FiltroAutencicacaoJWT;
import br.com.zup.zupayments.jwt.FiltroDeAutorizacao;
import br.com.zup.zupayments.jwt.UsuarioLoginService;
import br.com.zup.zupayments.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHES_GET = {
            "/fornecedores/**",
            "/pedidos/**",
            "/responsaveis/**",
            "/notas_fiscais/**"
    };

    private static final String[] PUBLIC_MATCHES_POST = {
            "/fornecedores/**",
            "/pedidos/**",
            "/responsaveis/**",
            "/notas_fiscais/**"
    };
    private static final String[] PUBLIC_MATCHES_PATCH = {
            "/fornecedores/**",
            "/pedidos/**",
            "/responsaveis/**",
            "/notas_fiscais/**"
    };


    @Autowired
    private ComponenteJWT componenteJWT;

    @Autowired
    private UsuarioLoginService usuarioLoginService;

    @Autowired
    private UsuarioService usuarioService;


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.cors();

        http.authorizeRequests()
                .antMatchers(HttpMethod.PATCH,PUBLIC_MATCHES_PATCH).hasRole(String.valueOf(RolesEnum.PERFIL_MASTER))
                .antMatchers(HttpMethod.POST,PUBLIC_MATCHES_POST).hasRole(String.valueOf(RolesEnum.PERFIL_MASTER))
                .antMatchers(HttpMethod.GET,PUBLIC_MATCHES_GET).hasRole(String.valueOf(RolesEnum.PERFIL_MASTER))
                .antMatchers(HttpMethod.GET,"/fornecedores/").hasRole(String.valueOf(RolesEnum.PERFIL_COMPRAS))
                .antMatchers(HttpMethod.POST,"/fornecedores/").hasRole(String.valueOf(RolesEnum.PERFIL_COMPRAS))
                .antMatchers(HttpMethod.PATCH,"/fornecedores/").hasRole(String.valueOf(RolesEnum.PERFIL_COMPRAS))
                .antMatchers(HttpMethod.GET,"/notas_ficais/").hasRole(String.valueOf(RolesEnum.PERFIL_COMPRAS))
                .antMatchers(HttpMethod.GET,"/pedidos/").hasRole(String.valueOf(RolesEnum.PERFIL_COMPRAS))
                .antMatchers(HttpMethod.POST,"/pedidos/").hasRole(String.valueOf(RolesEnum.PERFIL_COMPRAS))
                .antMatchers(HttpMethod.PATCH,"/pedidos/").hasRole(String.valueOf(RolesEnum.PERFIL_COMPRAS))
                .antMatchers(HttpMethod.GET,"/notas_ficais/").hasRole(String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.POST,"/notas_ficais/").hasRole(String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.PATCH,"/notas_ficais/").hasRole(String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.GET,"/responsaveis/").hasRole(String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.POST,"/responsaveis/").hasRole(String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.PATCH,"/responsaveis/").hasRole(String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.GET,"/pedidos/").hasRole(String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.POST, "/usuarios/").permitAll()
                .anyRequest().authenticated();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilter(new FiltroAutencicacaoJWT(componenteJWT, authenticationManager(), usuarioService));
        http.addFilter(new FiltroDeAutorizacao(authenticationManager(), componenteJWT, usuarioLoginService));

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    }

    @Bean
    CorsConfigurationSource configuracaoDeCors(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    protected BCryptPasswordEncoder critografarSenha() {
       return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioLoginService).passwordEncoder(critografarSenha());
    }
}
