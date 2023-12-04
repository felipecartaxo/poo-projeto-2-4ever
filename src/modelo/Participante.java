package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Participante {
	// Atributos
	private String cpf;
	private String nascimento;
	private ArrayList<Ingresso> ingressos = new ArrayList<Ingresso>(); // Relacionamento 1:N com a classe Ingresso
	
	// Construtor
	public Participante(String cpf, String nascimento) {
		super(); // Indica que é uma superclasse
		this.cpf = cpf;
		this.nascimento = nascimento;
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
	
	// Método para calcular a idade do participante
	public int calcularIdade() {
		DateTimeFormatter f;
		f = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formatando a idade
		LocalDate dataNasc = LocalDate.parse(this.nascimento, f);
        LocalDate hoje = LocalDate.now(); // Obtendo a data atual
        Period periodo = Period.between(dataNasc, hoje); // Calculando a diferença entre as datas
        
        return periodo.getYears(); // Retornando a idade em anos
	}
	
	// Método para retornar o valor do desconto do participante
	public double valorDesconto() {
		if(calcularIdade() < 18) {
			return 0.1;
		}
		else {
			if(calcularIdade() < 60) {
				return 1;
			}
			else {
				return 0.2;
			}
		}
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
}