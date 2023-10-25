package com.grupofive.demo.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupofive.demo.User.dto.UserRegisterDto;
import com.grupofive.demo.User.entities.User;
import com.grupofive.demo.User.enums.UserRole;
import com.grupofive.demo.User.repositories.UserRepository;
import com.grupofive.demo.security.dto.AuthenticationDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    //esse auth manager vem do security configuration
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserRepository repository;

    
    //Endpoint de login. Onde será feita a requisição
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto dto) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha()); //O authenticationService vai mostrar ao spring como buscar essas infomações no bd. Isso gera um token
        var auth = authManager.authenticate(userNamePassword); //O authManager do próprio spring irá autenticar o usuário

        return ResponseEntity.ok().build();
    }

    @PostMapping(value ="/register")
    public ResponseEntity register(@RequestBody @Valid UserRegisterDto dto){
        //Verificar se o login já existe no bd
        if(repository.findByLogin(dto.login()) != null) return ResponseEntity.badRequest().build();

        String encriptedPwd = new BCryptPasswordEncoder().encode(dto.senha());
        User user = new User(dto.login(), encriptedPwd, UserRole.USER);

        repository.save(user);
        return ResponseEntity.ok().build();
    }

}
