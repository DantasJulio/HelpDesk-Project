package com.julio.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.helpdesk.domain.Cliente;
import com.julio.helpdesk.domain.Pessoa;
import com.julio.helpdesk.domain.dto.ClienteDTO;
import com.julio.helpdesk.repositories.ClienteRepository;
import com.julio.helpdesk.repositories.PessoaRepository;
import com.julio.helpdesk.services.exception.DataIntegrityViolationException;
import com.julio.helpdesk.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired 
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById (Integer id) {
		Optional<Cliente> tecnico = repository.findById(id);
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id)) ;
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create (ClienteDTO objDTO) {
		objDTO.setId(null);
		validaPorCPFeEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id); //setando o id direto da url para evitar que o usuário passe outro id no corpo da requisição.
		Cliente oldObj = findById(id);
		validaPorCPFeEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
		} 
			repository.deleteById(id);		
	}
	
	private void validaPorCPFeEmail(ClienteDTO objDTO) {
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
