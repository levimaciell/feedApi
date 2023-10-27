package com.grupofive.demo.security_auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupofive.demo.User.dto.UserRegisterDto;
import com.grupofive.demo.security_auth.dto.AuthenticationDto;
import com.grupofive.demo.security_auth.dto.LoginResponseDto;
import com.grupofive.demo.security_auth.service.LoginAndRegisterService;


@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private LoginAndRegisterService service;
    
    //Endpoint de login. Onde será feita a requisição
    @PostMapping(value = "/login")
    public LoginResponseDto login(@RequestBody AuthenticationDto dto) {
        LoginResponseDto response = service.loginUser(dto);

        return response;
    }

    @PostMapping(value ="/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDto dto){
        
        service.registerUser(dto);

        return ResponseEntity.ok("Usuário criado com sucesso");
    }

}
