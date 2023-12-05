package modelo;

public class Ingresso {
	// Atributos
	private String codigo;
	private String telefone;
	
	private Evento evento; // Relacionamento 1:1 com a classe Evento
	private Participante participante; // Relacionamento 1:1 com a classe Participante
	
	// Construtor
	public Ingresso(String codigo, String telefone) {
		this.codigo = codigo;
		this.telefone = telefone;
	}

	// Getters e setters
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

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	
	// Métodos
	public double calcularPreco() { // A ser testado
		return evento.getPreco() * participante.valorDesconto();
	}

	@Override
	public String toString() {
        // Adiciona as informações requeridas ao toString
        return "\nCódigo do ingresso = " + codigo + "\nTelefone = " + telefone + "\nCódigo do evento = " + evento.getId() + "\nCPF do titular = " + participante.getCpf() + "\n--------------------";
    }
	
	
}