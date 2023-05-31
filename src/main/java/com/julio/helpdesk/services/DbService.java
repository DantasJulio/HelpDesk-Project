package com.julio.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julio.helpdesk.domain.Chamado;
import com.julio.helpdesk.domain.Cliente;
import com.julio.helpdesk.domain.Tecnico;
import com.julio.helpdesk.domain.enums.Perfil;
import com.julio.helpdesk.domain.enums.Prioridade;
import com.julio.helpdesk.domain.enums.Status;
import com.julio.helpdesk.repositories.ChamadoRepository;
import com.julio.helpdesk.repositories.ClienteRepository;
import com.julio.helpdesk.repositories.TecnicoRepository;

@Service
public class DbService {
	
	@Autowired
	ChamadoRepository chamadoRepository;
	
	@Autowired
	TecnicoRepository tecnicoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public void instanciaDB() {
		
		Tecnico tecnico = new Tecnico(null, "Julio Cesar", "10334330475", "juliodantas@mail.com", "1234");
		tecnico.addPerfis(Perfil.ADMIN);
		
		Cliente cliente = new Cliente(null, "Maressa Cabral", "11367706408", "maressarocha@mail.com", "4321");
		
		Chamado chamado = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado-0001", 
				"Requisição de Manutenção", tecnico, cliente);
		
		tecnicoRepository.saveAll(Arrays.asList(tecnico));
		clienteRepository.saveAll(Arrays.asList(cliente));
		chamadoRepository.saveAll(Arrays.asList(chamado));
		
		
	}

}
