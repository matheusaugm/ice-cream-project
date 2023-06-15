package com.example.projetoacai.services;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

import static com.example.projetoacai.services.DatabaseConnection.getConnection;

public class Main {
	public static void main(String[] args) throws SQLException, IOException {
		SelectorAndInserter selectorAndInserter = new AcaiteriaImpl(getConnection());
		User currentUser = null;
		Scanner scanner = new Scanner(System.in);
		boolean loggedIn = false;
		while(true){
			System.out.println("===== Acaí BH =====");
			System.out.println("1. Registrar");
			System.out.println("2. Login");
			System.out.println("3. Sair");
			System.out.print("Escolha uma opção: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option){
				case 1:
					System.out.print("Digite o nome: ");
					String name = scanner.nextLine();
					System.out.print("Digite seu telefone: ");
					String email = scanner.nextLine();
					System.out.print("Senha: ");
					String password = scanner.nextLine();
					User user = new User(name, email, password);
					selectorAndInserter.cadastrarUsuario(user);
					System.out.println("Usuário registrado com sucesso!");
					break;
				case 3:
					loggedIn=false;
					System.out.println("Saindo do programa...");
					System.exit(0);
					break;
			}
		}
	}
}
