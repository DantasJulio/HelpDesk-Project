package com.julio.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.helpdesk.domain.Tecnico;
import com.julio.helpdesk.domain.dto.TecnicoDTO;
import com.julio.helpdesk.repositories.TecnicoRepository;
import com.julio.helpdesk.services.exception.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired 
	private TecnicoRepository repository;
	
	public Tecnico findById (Integer id) {
		Optional<Tecnico> tecnico = repository.findById(id);
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id)) ;
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create (TecnicoDTO objDTO) {
		objDTO.setId(null);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);
	}



}
