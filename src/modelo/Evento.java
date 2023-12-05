package modelo;

import java.util.ArrayList;

public class Evento {
	// Atributos
	private int id;
	private String data;
	private String descricao;
	private int capacidade;
	private double preco;
	
	private ArrayList<Ingresso> ingressos = new ArrayList<Ingresso>(); // Relacionamento 1:N com a classe Ingresso
	
	// Construtor
	public Evento(int id, String data, String descricao, int capacidade, double preco) {
		this.id = id;
		this.data = data;
		this.descricao = descricao;
		this.capacidade = capacidade;
		this.preco = preco;
	}
	
	// Métodos
	public void adicionar(Ingresso i) {
		ingressos.add(i);
	}
	
	public void remover(Ingresso i) {
		ingressos.remove(i);
	}
	
	public Ingresso localizar(String codigo) {
		for(Ingresso i : ingressos) {
			if(i.getCodigo().equals(codigo)) {
				return i;
			}
		}
		return null;
	}
	
	public boolean lotado() { // Verifica se o evento está lotado
		/*
		if(ingressos.size() == capacidade) {
			return true;
		}
		else {
			return false;
		} */
		
	    return ingressos.size() >= capacidade;
	}
	
	// Retorna a quantidade de ingressos
	public int quantidadeIngressos() {
		return ingressos.size(); // A ser testado
	}
	
	// Retorna o valor total arrecadado pelo evento
	public double totalArrecadado() {
		return ingressos.size() * this.preco; // A ser testado 
	}

	
	// Getters e setters
	public int getId() {
		return id;
	}

	public String getData() {
		return data;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public double getPreco() {
		return preco;
	}

	private ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}

	// ToString
	@Override
	public String toString() {
		return "Evento [id=" + id + ", data=" + data + ", descricao=" + descricao + ", capacidade=" + capacidade
				+ ", preco=" + preco + ", ingressos=" + ingressos + "]";
	}
}