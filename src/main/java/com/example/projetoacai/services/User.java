package com.example.projetoacai.services;

public class User {
	private	int id;
	private	String nome;
	private	String telefone;
	private String senha;


	public void setId(int id) {
		this.id = id;
	}

	public User(String nome, String telefone, String senha) {
		this.nome = nome;
		this.telefone = telefone;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
