package com.julio.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julio.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
