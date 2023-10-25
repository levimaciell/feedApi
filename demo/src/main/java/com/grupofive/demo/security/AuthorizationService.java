package com.grupofive.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grupofive.demo.User.repositories.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    //Esse método é chamado toda vez que alguém tentar se autenticar nessa aplicação
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
    
}
