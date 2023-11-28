package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Participante {
	// Atributos
	private String cpf;
	private String nascimento;
	private ArrayList<Ingresso> ingressos = new ArrayList<Ingresso>();
	
	// Construtor
	public Participante(String cpf, String nascimento) {
		super(); // Indica que é uma superclasse
		this.cpf = cpf;
		this.nascimento = nascimento;
	}
	
	// Getters
	public String getCpf() {
		return cpf;
	}
	
	public String getNascimento() {
		return nascimento;
	}

	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}
	
	// ToString
	@Override
	public String toString() {
		return "Participante [cpf=" + cpf + ", nascimento=" + nascimento + ", ingressos=" + ingressos + "]";
	}
	
	// Métodos
	public int calcularIdade(LocalDate nascimento, LocalDate dataAtual) {
		return Period.between(nascimento, dataAtual).getYears();
	}
	
	// Relacionamento com a classe Aluno
	
}