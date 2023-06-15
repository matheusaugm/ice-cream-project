package com.example.projetoacai.services;

import java.io.IOException;

public interface SelectorAndInserter {

	void cadastrarUsuario(User user) throws IOException;

	void selecionarIngredientes(int id);

	User login(String email, String senha);

	int buscarProdutoPorNome(String nome);

	Pedido adicionarPedido(String telefone, String produto, boolean adicionais);
}
