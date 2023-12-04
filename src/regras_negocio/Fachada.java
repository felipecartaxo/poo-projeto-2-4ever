package regras_negocio;

import java.util.ArrayList;

import modelo.Convidado;
import modelo.Participante;
import repositorio.Repositorio;

public class Fachada {
	private static Repositorio repositorio = new Repositorio();
	
	// Construtor
	private Fachada() {}
	
	// Métodos
	// --------------------------------
	
	// criarParticipante()
	public static void criarParticipante(String cpf, String nascimento) throws Exception{
		Participante p = repositorio.localizarParticipante(cpf); // Verifica se o participante já está cadastrado
		
		if(p != null) { // Caso p seja diferente de nulo, ou seja, caso já exista algo com aquele mesmo cpf (que é o identificador do participante), lança uma exceção
			throw new Exception("Participante já cadastrado");
		}
		p = new Participante(cpf, nascimento); // Adiciona ao array
		repositorio.adicionar(p); // Adiciona ao repositório
		repositorio.salvarObjetos();
	}
	
	// criarConvidado
	public static void criarConvidado(String cpf, String nascimento, String empresa) throws Exception{
		Convidado c = (Convidado) repositorio.localizarParticipante(cpf); // Como o método localizarParticipante retorna um objeto do tipo Participante, é necessário fazer o casting
		
		if(c != null) {
			throw new Exception("Convidado já cadastrado");
		}
		
		// Verificar se é necessário criar um método diferente para os Convidados na classe Repositorio
		c = new Convidado(cpf, nascimento, empresa);
		repositorio.adicionar(c);
		repositorio.salvarObjetos();
	}
	
	public static ArrayList<Participante> listarParticipantes() {
		return repositorio.getParticipantes();
	}
	
}
