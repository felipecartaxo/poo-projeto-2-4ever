package modelo;

public class Convidado extends Participante{
	// Atributos
	private String empresa;
	
	// Construtor
	public Convidado(String cpf, String nascimento, String empresa) {
		super(cpf, nascimento); // Indica que é uma subclasse e que herdará estes atributos da superclasse
		this.empresa = empresa;
	}
	
	// Obs: A classe Convidade já irá herdar o método calcularIdade(), logo não é necessário criá-lo novamente
	
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
}