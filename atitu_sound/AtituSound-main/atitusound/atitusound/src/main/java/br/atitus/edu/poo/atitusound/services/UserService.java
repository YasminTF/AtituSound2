package br.atitus.edu.poo.atitusound.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.atitus.edu.poo.atitusound.entities.UserEntity;

public interface UserService extends GenericService<UserEntity>,  UserDetailsService{
	
	

}
