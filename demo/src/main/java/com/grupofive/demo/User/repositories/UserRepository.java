package com.grupofive.demo.User.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.grupofive.demo.User.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
    
    //Só é possível retornar um userDetails, pois a classe usuário implementa essa interface. 
    UserDetails findByLogin(String login);
}
