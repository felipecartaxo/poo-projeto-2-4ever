package regras_negocio;

import java.util.ArrayList;

import modelo.Convidado;
import modelo.Evento;
import modelo.Participante;
import repositorio.Repositorio;

public class Fachada {
	private static Repositorio repositorio = new Repositorio();
	
	// Construtor
	private Fachada() {}
	
	// Métodos
	
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
	
	// listarParticipantes
	public static ArrayList<Participante> listarParticipantes() {
		return repositorio.getParticipantes();
	}
	
	// criarEvento
	public static void criarEvento(String data, String descricao, int capacidade, double preco) throws Exception{
		// O evento pode ter preço 0 (nesse caso, seria considerado um evento beneficente
		if(preco < 0) {
			throw new Exception("Preço negativo"); // Lança exceção caso o preço for negativo
		}
		if(capacidade < 2) {
			throw new Exception("A capacidade do evento deve ser de no mínimo 2 ingressos"); // Lança exceção caso a capacidade do evento for inferior a 2 ingressos
		}
		if(data == null || data.isEmpty()) {
			throw new Exception("A data do evento é obrigatória"); // Lança exceção se a data do evento não for especificada
		}
		if(descricao == null || descricao.isEmpty()) {
			throw new Exception("A descrição do evento é obrigatório"); // Lança exceção se a descrição do evento não for especificada
		}
		
		int id = repositorio.gerarId();
		Evento e = new Evento(id, data, descricao, capacidade, preco);
		repositorio.adicionar(e);
		repositorio.salvarObjetos();
	}
	
	// listarEventos
	public static ArrayList<Evento> listarEventos() {
		return repositorio.getEventos();
	}
	
}
