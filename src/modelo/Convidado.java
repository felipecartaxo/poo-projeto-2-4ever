package modelo;

import java.time.LocalDate;
import java.time.Period;

public class Convidado extends Participante{
	// Atributos
	private String empresa;
	
	// Construtor
	public Convidado(String cpf, String nascimento, String empresa) {
		super(cpf, nascimento); // Indica que é uma subclasse e que herdará estes atributos da superclasse
		this.empresa = empresa;
	}
	
	// Getters
	public String getEmpresa() {
		return empresa;
	}

	// ToString
	@Override
	public String toString() {
		return "Convidado [empresa=" + empresa + ", getCpf()=" + getCpf() + ", getNascimento()=" + getNascimento()
				+ ", getIngressos()=" + getIngressos() + "]";
	}
	
	// Métodos
	@Override
	public int calcularIdade(LocalDate nascimento, LocalDate dataAtual) {
		return Period.between(nascimento, dataAtual).getYears();
	}
}
