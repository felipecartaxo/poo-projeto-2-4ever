package regras_negocio;

import java.util.ArrayList;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import repositorio.Repositorio;

public class Fachada {
	private static Repositorio repositorio = new Repositorio();
	
	// Construtor
	private Fachada() {}
	
	// Métodos
	
	// criarParticipante()
	public static void criarParticipante(String cpf, String nascimento) throws Exception{
		if(nascimento == null || nascimento.isEmpty()) {
			throw new Exception("Favor informar uma data"); // Lança uma exceção caso não seja informada uma data
		}
		
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
		if(empresa.isBlank() || empresa.isEmpty() || empresa == null) {
			throw new Exception("Favor informar o nome de uma empresa");
		}
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

	//	criarIngresso
	public static void criarIngresso(int id, String cpf, String telefone) throws Exception {
		Evento e = repositorio.localizarEvento(id);
		Participante p = repositorio.localizarParticipante(cpf);
		
		if (telefone == null || telefone.isEmpty()) {
			throw new Exception("O número do telefone é obrigatório"); // Lança exceção, caso o número do telefone for nulo
		}
		if (e == null) {
			throw new Exception("Evento não encontrado"); // Lança exceção caso o evento não exista
		}
		if (p == null) {
			throw new Exception("Participante não encontrado"); // Lança exceção caso o participante não exista
		}
		if(e.lotado()) {
			throw new Exception("Evento lotado"); // Lança exceção caso o evento esteja lotado
		}

		String codigo = id + "-" + cpf;
		Ingresso ingressoExistente = repositorio.localizarIngresso(codigo);
		
		if(ingressoExistente != null) {
			throw new Exception("Ingresso já cadastrado");
		}
		
		// Criando o ingresso propriamente dito
		Ingresso ingresso = new Ingresso(codigo, telefone);

	        
		// Configurar relacionamento bidirecional
		ingresso.setEvento(e);
		ingresso.setParticipante(p);
	        
		// Adicionar o ingresso ao evento e ao participante
		e.adicionar(ingresso);
		p.adicionar(ingresso);
	        
		// Adicionar o ingresso ao repositório
		repositorio.adicionar(ingresso);
	        
		// Salvar objetos no repositório
		repositorio.salvarObjetos();
	}

	// listarIngressos
	public static ArrayList<Ingresso> listarIngressos() {
		return repositorio.getIngressos();
	}
	    
	// apagarEvento
	public static void apagarEvento(int id) throws Exception{
		Evento e = repositorio.localizarEvento(id);

		if (e == null) {
			throw new Exception("Evento não encontrado"); // Lança exceção caso o evento não exista
		}
		if(e.quantidadeIngressos() > 0) {
			throw new Exception("O evento possui ingressos"); // Caso o evento possua ingressos, não será possível apagá-lo e será lançada uma exceção
		}
	    	
		repositorio.remover(e);
		repositorio.salvarObjetos();
	}
	    
	// apagarIngresso
	public static void apagarIngresso(String codigo) throws Exception{
		Ingresso i = repositorio.localizarIngresso(codigo);
	        
		if(i == null) {
			throw new Exception("Ingresso não encontrado");
		}

		i.getEvento().remover(i); // Remove o ingresso do evento
		i.getParticipante().remover(i); // Remove o ingresso do participante
	        
		repositorio.remover(i); // Remove o ingresso do repositório
		repositorio.salvarObjetos(); // Salva objetos no repositório
	}
	    
	// apagarParticipante
	    
	public static void apagarParticipante(String cpf) throws Exception {
		Participante p = repositorio.localizarParticipante(cpf);

		if (p == null) { // Lança exceção caso o participante não seja encontrado
			throw new Exception("Participante não encontrado");
		}
		if(p.getIngressos().size() > 0) { // Lança exceção se o participante ainda possuir ingressos
			throw new Exception("O participante ainda possui ingresso");
		}

		// Verifica se o último ingresso do participante está ultrapassado
		Ingresso ultimoIngresso;

		if (p.getIngressos().isEmpty()) { // Talvez essa parte possa ser removida para que pare de aparecer o erro "ultimoIngresso" is null
			ultimoIngresso = null;
		}
		else {
			ultimoIngresso = p.getIngressos().get(p.getIngressos().size() - 1);
		}

		if (ultimoIngresso.verificaIngressoUltrapassado()) {
			throw new Exception("O último ingresso não está ultrapassado");
		}

		// Remove todos os ingressos associados ao participante
		for (Ingresso ingresso : new ArrayList<>(p.getIngressos())) {
			apagarIngresso(ingresso.getCodigo());
		}

		// Remove o participante do repositório
		repositorio.remover(p);
		repositorio.salvarObjetos();
	}
}