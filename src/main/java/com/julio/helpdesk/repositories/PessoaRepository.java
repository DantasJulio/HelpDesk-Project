package com.julio.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julio.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
