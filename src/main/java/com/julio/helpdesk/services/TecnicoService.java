package com.julio.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.helpdesk.domain.Pessoa;
import com.julio.helpdesk.domain.Tecnico;
import com.julio.helpdesk.domain.dto.TecnicoDTO;
import com.julio.helpdesk.repositories.PessoaRepository;
import com.julio.helpdesk.repositories.TecnicoRepository;
import com.julio.helpdesk.services.exception.DataIntegrityViolationException;
import com.julio.helpdesk.services.exception.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired 
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById (Integer id) {
		Optional<Tecnico> tecnico = repository.findById(id);
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id)) ;
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create (TecnicoDTO objDTO) {
		objDTO.setId(null);
		validaPorCPFeEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);
	}

	private void validaPorCPFeEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado para o usuário: " 
														+ obj.get().getId() + ", Nome: "  + obj.get().getNome());
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado para o usuário com Id: " 
														+  obj.get().getId() + ", Nome: " + obj.get().getNome());		
		}
	}



}
