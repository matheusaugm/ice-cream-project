package com.example.projetoacai.services;

import java.io.*;
import java.sql.*;


public class AcaiteriaImpl implements SelectorAndInserter {
	private static final String SELECT_INGREDIENTS_BY_ID = "SELECT * FROM ingredientes WHERE id = ?";
	private static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE email = ? AND password = ?";

	private static final String SELECT_INGREDIENTS_BY_NAME = "SELECT * FROM ingredientes WHERE nome = ?";
	private static final String SELECT_ORDER_BY_CLIENT_ID = "SELECT id FROM pedidos WHERE cliente_id = ?";
	private static final String SELECT_FINAL_VALUE_BY_ORDER_ID = "SELECT p.id AS pedido_id,	SUM(pr.preco) AS valor_produtos, SUM(a.preco) AS valor_adicionais, SUM(pr.preco) + SUM(a.preco) AS valor_total FROM pedidos p JOIN produtos pr ON p.produto_id = pr.id LEFT JOIN pedidos_adicionais pa ON p.id = pa.pedido_id LEFT JOIN adicionais a ON pa.adicional_id = a.id WHERE p.id = ? GROUP BY p.id";
	private static final String INSERT_ORDER = "INSERT INTO pedidos (cliente_id, produto_id, quantidade) VALUES(?, ?, ?)";
	private static final String INSERT_ORDER_ADDITIONAL = "INSERT INTO pedidos_adicionais (pedido_id, adicional_id) VALUES(?, ?)";
	private static final String INSERT_USER = "INSERT INTO users (nome, telefone, endereco, senha) VALUES(?, ?, ?, ?)";

	private Connection connection;

	public AcaiteriaImpl(Connection connection) {
		this.connection = connection;

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

	@Override
	public void selecionarIngredientes(int id) {
		try (PreparedStatement statement = connection.prepareStatement(SELECT_INGREDIENTS_BY_ID)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					System.out.println("ID: " + resultSet.getInt("id"));
					System.out.println("Nome: " + resultSet.getString("nome"));
					System.out.println("====================================");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao selecionar ingredientes: " + e.getMessage());
			LogWriter logWriter = new LogWriter();
			logWriter.escreverLog("Erro ao selecionar ingredientes: " + e.getMessage(), "select_ingredients_log.txt");
		}
	}

	@Override
	public User login(String email, String senha) {
		try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_QUERY)) {
			statement.setString(1, email);
			statement.setString(2, senha);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int userId = resultSet.getInt("id");
					String nome = resultSet.getString("name");
					String userEmail = resultSet.getString("email");
					String userSenha = resultSet.getString("password");
					User user = new User(nome, userEmail, userSenha);
					user.setId(userId);
					return user;
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao fazer login: " + e.getMessage());
		}
		return null;
	}

	public User buscarUsuarioPorTelefone(String telefone) {
		String query = "SELECT * FROM users WHERE telefone = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, telefone);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int userId = resultSet.getInt("id");
					String nome = resultSet.getString("name");
					String userEmail = resultSet.getString("telefone");
					String senha = resultSet.getString("password");
					String endereco = resultSet.getString("endereco");
					User user = new User(nome, userEmail, senha);
					user.setId(userId);
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar usuário pelo telefone: " + e.getMessage());
			LogWriter logWriter = new LogWriter();
			logWriter.escreverLog("Erro ao buscar usuário pelo telefone: " + e.getMessage(), "select_user_by_phone_log.txt");
		}
		return null;
	}

	@Override
	public int buscarProdutoPorNome(String nome) {
		String query = "SELECT * FROM produtos WHERE nome = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, nome);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					return resultSet.getInt("id");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar produto pelo nome: " + e.getMessage());
			LogWriter logWriter = new LogWriter();
			logWriter.escreverLog("Erro ao buscar produto pelo nome: " + e.getMessage(), "select_product_by_name_log.txt");
		}
		return 0;
	}

	@Override
	public int adicionarPedido(String telefone, String produto, boolean adicionais) {

		User user = buscarUsuarioPorTelefone(telefone);
		int idProduto = buscarProdutoPorNome(produto);
		if (user != null) {
			try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
				statement.setInt(1, user.getId());
				statement.setInt(2, idProduto);
				statement.setInt(3, 1);
				statement.setBoolean(4, adicionais);
				int affectedRows = statement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet resultSet = statement.getGeneratedKeys()) {
						if (resultSet.next()) {
							int idPedido = resultSet.getInt(1);
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erro ao adicionar pedido: " + e.getMessage());
				LogWriter logWriter = new LogWriter();
				logWriter.escreverLog("Erro ao adicionar pedido: " + e.getMessage(), "add_order_log.txt");
			}
		else{
				System.out.println("Usuário não encontrado");

			}
			return 0;
		}
	}


	public	Pedido buscarValorPedido(int idCheckout){
			try (PreparedStatement statement = connection.prepareStatement(SELECT_FINAL_VALUE_BY_ORDER_ID)) {
				statement.setInt(1, idCheckout);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						double valorTotal = resultSet.getDouble("valor_total");
						double valorAdicionais = resultSet.getDouble("valor_adicionais");
						double valorProdutos = resultSet.getDouble("valor_produtos");
						Pedido pedidoCheckout = new Pedido(valorTotal, valorProdutos, valorAdicionais, idCheckout, 1);
						return pedidoCheckout;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erro ao buscar valor do pedido: " + e.getMessage());
				LogWriter logWriter = new LogWriter();
				logWriter.escreverLog("Erro ao buscar valor do pedido: " + e.getMessage(), "select_order_value_log.txt");

			}
		}
	}
}