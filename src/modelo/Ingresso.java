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
		return evento.getPreco() - (evento.getPreco() * participante.valorDesconto()); // Calcula o valor do desconto e depois retira do valor inteiro do ingresso
	}

	@Override
	public String toString() {
        return "\nIngresso ===> Cod = " + codigo + " | Tel = " + telefone + " | Cod Evento = " + evento.getId() + " | CPF = " + participante.getCpf();
    }
}