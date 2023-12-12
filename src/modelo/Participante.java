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
	
	public Ingresso localizar(String codigo) { // Verificar se este método é realmente necessário, visto que apenas segui o exemplo da classe Prateleteira
		for(Ingresso i : ingressos) {
			if(i.getCodigo().equals(codigo)) {
				return i;
			}
		}
		return null;
	}
	
	public int calcularIdade() { // Método para calcular a idade do participante
		DateTimeFormatter f;
		f = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Setando o tipo de formatação da data
		
		LocalDate dataNasc = LocalDate.parse(this.nascimento, f); // Aplicando a formatação à idade do participante
        LocalDate hoje = LocalDate.now(); // Obtendo a data atual
        Period periodo = Period.between(dataNasc, hoje); // Calculando a diferença entre as datas
        
        return periodo.getYears(); // Retornando a idade (em anos)
	}
	
	//Método auxiliar para retornar o valor do desconto do participante
	public double valorDesconto() {
		if(calcularIdade() < 18) {
			return 0.1; // Se for menor de idade, 10% de desconto
		}
		else {
			if(calcularIdade() < 60) {
				return 0; // Se for maior de idade e não for idoso, o preço permanece normal
			}
			else {
				return 0.2; // Se for idoso, 20% de desconto
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
	
	// Setters
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	
	// ToString
	@Override
	public String toString() {
		return "Participante: CPF = " + cpf + "; Data nasc. = " + nascimento + "; Ingressos = " + ingressos + ";";
	}
}