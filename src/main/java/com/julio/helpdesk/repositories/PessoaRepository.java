package com.julio.helpdesk.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julio.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
	Optional<Pessoa> findByCpf(Object cpf);
	Optional<Pessoa> findByEmail(Object email);

}
