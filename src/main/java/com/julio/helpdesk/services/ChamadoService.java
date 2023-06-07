package com.julio.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.helpdesk.domain.Chamado;
import com.julio.helpdesk.domain.Cliente;
import com.julio.helpdesk.domain.Tecnico;
import com.julio.helpdesk.domain.Chamado;
import com.julio.helpdesk.domain.dto.ChamadoDTO;
import com.julio.helpdesk.domain.enums.Prioridade;
import com.julio.helpdesk.domain.enums.Status;
import com.julio.helpdesk.repositories.ChamadoRepository;
import com.julio.helpdesk.services.exception.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	ChamadoRepository repository;
	
	@Autowired
	TecnicoService tecnicoService;
	
	@Autowired
	ClienteService clienteService;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
	}
	
	public List<Chamado> findAll() {
		return repository.findAll();
	}
	
	public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id); 
		Chamado oldObj = findById(id);
		oldObj = newChamado(objDTO);
		return repository.save(oldObj);
	}

	public Chamado create(@Valid ChamadoDTO obj) {
		return repository.save(newChamado(obj));
	}

	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnicoId());
		Cliente cliente = clienteService.findById(obj.getClienteId());
		
		Chamado chamado = new Chamado();
		
		if (obj.getId() != null) {
			chamado.setId(obj.getId()); 
		}
		
		chamado.setCliente(cliente);
		chamado.setTecnico(tecnico);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridadeId()));
		chamado.setStatus(Status.toEnum(obj.getStatusId()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		
		return chamado;
	}
	
}
