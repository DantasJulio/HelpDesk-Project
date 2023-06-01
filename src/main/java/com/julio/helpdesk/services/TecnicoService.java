package com.julio.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.helpdesk.domain.Tecnico;
import com.julio.helpdesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {
	
	@Autowired 
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico findById (Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico.orElse(null);
	}

}
