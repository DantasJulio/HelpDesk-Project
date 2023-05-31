package com.julio.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julio.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
