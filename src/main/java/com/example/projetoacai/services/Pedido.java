package com.example.projetoacai.services;

public class Pedido {
	private double valorTotal;
	private double valorProdutos;
	private double valorAdicionais;
	private int id;
	private int quantidade;

	public double getValorTotal() {
		return valorTotal;
	}

	public Pedido(double valorTotal, double valorProdutos, double valorAdicionais, int id, int quantidade) {
		this.valorTotal = valorTotal;
		this.valorProdutos = valorProdutos;
		this.valorAdicionais = valorAdicionais;
		this.id = id;
		this.quantidade = quantidade;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getValorProdutos() {
		return valorProdutos;
	}

	public void setValorProdutos(double valorProdutos) {
		this.valorProdutos = valorProdutos;
	}

	public double getValorAdicionais() {
		return valorAdicionais;
	}

	public void setValorAdicionais(double valorAdicionais) {
		this.valorAdicionais = valorAdicionais;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}