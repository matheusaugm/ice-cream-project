package com.example.projetoacai.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class LogWriter {
	private static final String LOG_FOLDER = "logs/";

	public void escreverLog(String mensagem, String nomeArquivo) {
		try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(LOG_FOLDER + nomeArquivo, true))) {
			String logMessage = formatarMensagemLog(mensagem);
			logWriter.append(logMessage);
			logWriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao escrever no arquivo de log: " + e.getMessage());
		}
	}

	private String formatarMensagemLog(String mensagem) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		String formattedDateTime = now.format(formatter);
		return "[" + formattedDateTime + "] " + mensagem;
	}
}