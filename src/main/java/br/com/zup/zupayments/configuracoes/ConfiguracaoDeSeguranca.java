package br.com.zup.zupayments.configuracoes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHES_GET = {
            "/fornecedores/**",
            "/notas_ficais/**",
            "/pedidos/**",
            "/responsaveis/**"
    };
    private static final String[] PUBLIC_MATCHES_POST = {
            "/fornecedores/**",
            "/notas_ficais/**",
            "/pedidos/**",
            "/responsaveis/**"
    };
    private static final String[] PUBLIC_MATCHES_PATCH = {
            "/fornecedores/**",
            "/notas_ficais/**",
            "/pedidos/**",
            "/responsaveis/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.cors();

        http.authorizeRequests().antMatchers(HttpMethod.GET,PUBLIC_MATCHES_GET).permitAll()
                .antMatchers(HttpMethod.POST,PUBLIC_MATCHES_POST).permitAll()
                .antMatchers(HttpMethod.PATCH,PUBLIC_MATCHES_PATCH).permitAll()
                .anyRequest().authenticated();
    }
    @Bean
    CorsConfigurationSource configuracaoDeCors(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
