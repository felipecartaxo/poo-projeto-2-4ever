package modelo;

public class Convidado extends Participante {
	
	// Atributos
	private String empresa;
	
	// Construtor
	public Convidado(String cpf, String nascimento, String empresa) {
		super(cpf, nascimento); // Indica que é uma subclasse e que herdará estes atributos da superclasse Participante
		this.empresa = empresa;
	}
	
	// Sobreescrita do método calcularDesconto
	@Override
	public double valorDesconto() {
		if(calcularIdade() < 18) {
			return 0.1 + 0.5; // Se for menor de idade e por ser convidado, o desconto total é 10% + 50%, totalizando 60% de desconto
		}
		else {
			if(calcularIdade() < 60) {
				return 0; // Se for maior de idade e não for idoso, o preço permanece normal
			}
			else {
				return 0.2 + 0.5; // Se for idoso e por ser convidado, o desconto total é 20% + 50%, totalizando 70% de desconto
			}
		}
	}
	
	// Getters
	public String getEmpresa() {
		return empresa;
	}
	
	// Setters
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	// ToString
	@Override
	public String toString() {
		return "Convidado: CPF = " + getCpf() + "; Data nasc. = " + getNascimento() + "; Emp = " + empresa + "; Ingressos = " + getIngressos() + ";";
	}
}