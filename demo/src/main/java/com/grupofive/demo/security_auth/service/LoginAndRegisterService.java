package com.grupofive.demo.security_auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.grupofive.demo.User.dto.UserRegisterDto;
import com.grupofive.demo.User.entities.User;
import com.grupofive.demo.User.enums.UserRole;
import com.grupofive.demo.User.repositories.UserRepository;
import com.grupofive.demo.security_auth.LoginRegisterException;
import com.grupofive.demo.security_auth.TokenService;
import com.grupofive.demo.security_auth.dto.AuthenticationDto;
import com.grupofive.demo.security_auth.dto.LoginResponseDto;

@Service
public class LoginAndRegisterService {

    //esse auth manager vem do security configuration
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService service;
    @Autowired
    private UserRepository repository;

    public LoginResponseDto loginUser(AuthenticationDto dto){
        try{
            var userNamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha()); //O authenticationService vai mostrar ao spring como buscar essas infomações no bd. Isso gera um token
            var auth = authManager.authenticate(userNamePassword); //O authManager do próprio spring irá autenticar o usuário

            var token = service.generateToken((User) auth.getPrincipal());
            return new LoginResponseDto(dto.login(), token);
        }
        catch(BadCredentialsException e){
            throw new LoginRegisterException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch(InternalAuthenticationServiceException f){
            throw new LoginRegisterException("Usuário ou senha inválida", HttpStatus.BAD_REQUEST);
        }
    }

    public void registerUser(UserRegisterDto dto) {

        //Verificar se o login já existe no bd
        if(repository.findByLogin(dto.login()) != null)
            throw new LoginRegisterException("Não é possível criar usuário com este nome. Escolha outro nome de usuário", HttpStatus.FORBIDDEN);

        String encriptedPwd = new BCryptPasswordEncoder().encode(dto.senha());
        User user = new User(dto.login(), encriptedPwd, UserRole.USER);

        repository.save(user);
    }
}
