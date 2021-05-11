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
                .antMatchers(HttpMethod.POST, "/fornecedores/**").hasAnyAuthority(String.valueOf(RolesEnum.PERFIL_MASTER), String.valueOf(RolesEnum.PERFIL_COMPRAS))
                .antMatchers(HttpMethod.GET, "/fornecedores/**").hasAnyAuthority(String.valueOf(RolesEnum.PERFIL_MASTER), String.valueOf(RolesEnum.PERFIL_COMPRAS))
                .antMatchers(HttpMethod.PATCH, "/fornecedores/**").hasAnyAuthority(String.valueOf(RolesEnum.PERFIL_MASTER), String.valueOf(RolesEnum.PERFIL_COMPRAS))
                .antMatchers(HttpMethod.POST, "/pedidos/**").hasAnyAuthority(String.valueOf(RolesEnum.PERFIL_MASTER), String.valueOf(RolesEnum.PERFIL_COMPRAS))
                .antMatchers(HttpMethod.GET, "/pedidos/**").hasAnyAuthority(String.valueOf(RolesEnum.PERFIL_MASTER),String.valueOf(RolesEnum.PERFIL_COMPRAS),String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.PATCH, "/pedidos/**").hasAnyAuthority(String.valueOf(RolesEnum.PERFIL_MASTER), String.valueOf(RolesEnum.PERFIL_COMPRAS))
                .antMatchers(HttpMethod.POST, "/notas_ficais/**").hasAnyAuthority(String.valueOf(RolesEnum.PERFIL_MASTER), String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.GET, "/notas_ficais/**").hasAnyAuthority(String.valueOf(RolesEnum.PERFIL_MASTER),String.valueOf(RolesEnum.PERFIL_COMPRAS),String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.PATCH, "/notas_ficais/**").hasAnyAuthority(String.valueOf(RolesEnum.PERFIL_MASTER), String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.POST, "/responsaveis/**").hasAnyAuthority(String.valueOf(RolesEnum.PERFIL_MASTER), String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.GET, "/responsaveis/**").hasAnyAuthority(String.valueOf(RolesEnum.PERFIL_MASTER),String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.PATCH, "/responsaveis/**").hasAnyAuthority(String.valueOf(RolesEnum.PERFIL_MASTER), String.valueOf(RolesEnum.PERFIL_FINANCEIRO))
                .antMatchers(HttpMethod.POST, "/usuarios/").permitAll()
                .antMatchers(HttpMethod.GET, "/usuarios/").permitAll()
                .antMatchers(HttpMethod.PATCH, "/usuarios/**").permitAll()
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
