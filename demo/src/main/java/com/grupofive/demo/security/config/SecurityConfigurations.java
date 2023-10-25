package com.grupofive.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Essa anotação indica ao spring que esta é uma classe de configuração
@EnableWebSecurity //Essa anotação diz ao spring security para usar a configuração web desta classe
public class SecurityConfigurations {
    
    @Bean //Serve para dizer ao spring para carregar essa classe e fazer a injeção de dependencia
    //Esse método terá um conjunto de outros métodos que fará o filtro de segurança da minha requisição
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity
        .csrf(csrf -> csrf.disable())//Desativa o csrf(assumo que não seja ideal em prod) 
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//Autenticação stateless ao invés de statefull, onde o usuário é validado e recebe um token, e fica a cargo da api de validar se esse token foi criado pela api ou não
        .build();
        
    }




}
