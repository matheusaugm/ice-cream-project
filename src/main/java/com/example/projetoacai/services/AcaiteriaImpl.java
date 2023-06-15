package com.example.projetoacai.services;

import java.io.*;
import java.sql.*;

import java.time.*;
import java.time.format.*;
import java.util.*;


public class AcaiteriaImpl implements SelectorAndInserter{
	private static final String SELECT_INGREDIENTS_BY_ID = "SELECT * FROM ingredientes WHERE id = ?";
	private static final String SELECT_INGREDIENTS_BY_NAME = "SELECT * FROM ingredientes WHERE nome = ?";
	private static final String SELECT_ORDER_BY_CLIENT_ID = "SELECT id FROM pedidos WHERE id_cliente = ?";
	private static final String SELECT_FINAL_VALUE_BY_ORDER_ID = "SELECT p.id AS pedido_id,	SUM(pr.preco) AS valor_produtos, SUM(a.preco) AS valor_adicionais, SUM(pr.preco) + SUM(a.preco) AS valor_total FROM pedidos p JOIN produtos pr ON p.produto_id = pr.id LEFT JOIN pedidos_adicionais pa ON p.id = pa.pedido_id LEFT JOIN adicionais a ON pa.adicional_id = a.id WHERE p.id = ? GROUP BY p.id";
	private static final String INSERT_ORDER = "INSERT INTO pedidos (cliente_id, produto_id, quantidade) VALUES(?, ?, ?)";
	private static final String INSERT_ORDER_ADDITIONAL = "INSERT INTO pedidos_adicionais (pedido_id, adicional_id) VALUES(?, ?)";
	private static final String INSERT_USER = "INSERT INTO clientes (nome, telefone, senha) VALUES(?, ?, ?)";

	private Connection connection;
	public AcaiteriaImpl(Connection connection){this.connection = connection;

	}
	@Override
	public void cadastrarUsuario(User user) throws IOException {
		try (PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
			((PreparedStatement) statement).setString(1, user.getNome());
			statement.setString(2, user.getTelefone());
			statement.setString(3, user.getSenha());
			int affectedRows = statement.executeUpdate();
			if (affectedRows > 0) {
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						user.setId(generatedKeys.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao cadastrar usuario: " + e.getMessage());
			LogWriter logWriter = new LogWriter();
			logWriter.escreverLog("Erro ao cadastrar usuario: " + e.getMessage(), "register_user_log.txt");
		}
	}


}



