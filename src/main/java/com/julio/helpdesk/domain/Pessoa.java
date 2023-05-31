package com.julio.helpdesk.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.julio.helpdesk.domain.enums.Perfil;

import lombok.Data;

@Entity
public abstract class Pessoa {
	
	@Id
	protected Integer id;
	protected String nome;
	protected String cpf;
	protected String email;
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>(); // uma lista de perfis, pois um usuário pode ter mais de um perfil. Técnico ou Adm.
	protected LocalDate dataCriacao = LocalDate.now();
	
	public Pessoa() {
		super();
		addPerfis(Perfil.CLIENTE); // Opcional: todo usuário será necessariamente um cliente.
	}

	public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet()); // Mapeando a lista de perfis a partir do código.
	}

	public void addPerfis(Perfil perfil) {
		this.perfis.add(perfil.getCodigo()); // adicionando um perfil a partir do código do tipo de perfil.
		
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}	

}
