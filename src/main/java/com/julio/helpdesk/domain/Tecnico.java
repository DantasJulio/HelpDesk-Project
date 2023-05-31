package com.julio.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Tecnico extends Pessoa {
	
	private List<Chamado> chamados = new ArrayList<>();
	
	
	public Tecnico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		// TODO Auto-generated constructor stub
	}

}
