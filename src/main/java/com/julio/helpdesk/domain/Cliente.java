package com.julio.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


public class Cliente extends Pessoa {
	
	private List<Chamado> chamados = new ArrayList<>();
	
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		// TODO Auto-generated constructor stub
	}

	public void abrirChamado() {
		Chamado chamado = new Chamado();
		this.chamados.add(chamado);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}
