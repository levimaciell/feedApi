package com.grupofive.demo.security_auth.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.grupofive.demo.security_auth.filter.SecurityFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration //Essa anotação indica ao spring que esta é uma classe de configuração
@EnableWebSecurity //Essa anotação diz ao spring security para usar a configuração web desta classe
public class SecurityConfigurations {
    @Autowired
    private SecurityFilter securityFilter;
    
    //Serve para dizer ao spring para carregar essa classe e fazer a injeção de dependencia
    //Esse método terá um conjunto de outros métodos que fará o filtro de segurança da minha requisição
    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{

        return httpSecurity
        .cors(withDefaults()).csrf(csrf -> csrf.disable())//Desativa o csrf(assumo que não seja ideal em prod) 
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//Autenticação stateless ao invés de statefull, onde o usuário é validado e recebe um token, e fica a cargo da api de validar se esse token foi criado pela api ou não
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(new AntPathRequestMatcher("/auth/register", HttpMethod.POST.name())).permitAll() //permitindo não autenticação ao criar user
            .requestMatchers(new AntPathRequestMatcher("/auth/login", HttpMethod.POST.name())).permitAll() //permitindo não autenticação ao logar
            .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll() //permitindo não autenticação do console h2
            .anyRequest().authenticated() //Diz que qualquer requisição da minha api deve ser autenticada. 
        )//Quais requisições http devem ser autorizadas
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
        
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Bcrypt é uma biblioteca de criptografia. Nesse caso, senhas serão criptografadas no banco de dados
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    

    @Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD",
        "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
