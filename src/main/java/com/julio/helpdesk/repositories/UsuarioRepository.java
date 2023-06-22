package com.julio.helpdesk.repositories;

import org.springframework.data.repository.CrudRepository;

import com.julio.helpdesk.domain.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	Usuario findByLogin(String login);
	
	
}
