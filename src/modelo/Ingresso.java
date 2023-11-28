package modelo;

public class Ingresso {
	// Atributos
	private String codigo;
	private String telefone;
	private Evento evento;
	private Participante participante;
	
	// Construtor
	public Ingresso(String codigo, String telefone, Evento evento, Participante participante) {
		this.codigo = codigo;
		this.telefone = telefone;
		this.evento = evento;
		this.participante = participante;
	}

	// Getters
	public String getCodigo() {
		return codigo;
	}

	public String getTelefone() {
		return telefone;
	}

	public Evento getEvento() {
		return evento;
	}

	public Participante getParticipante() {
		return participante;
	}

	// ToString
	@Override
	public String toString() {
		return "Ingresso [codigo=" + codigo + ", telefone=" + telefone + ", evento=" + evento + ", participante="
				+ participante + "]";
	}
	
	// Métodos
	
	// Relacionamento com a classe Evento
	
	
	// Relacionamento com a classe Participante
	
	
	
}
