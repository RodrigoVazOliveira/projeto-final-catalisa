package br.com.zup.zupayments.configuracoes;

import br.com.zup.zupayments.enums.RolesEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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

                .anyRequest().authenticated();
        
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuarios/")
                .permitAll()
                .anyRequest()
                .authenticated();
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
}
