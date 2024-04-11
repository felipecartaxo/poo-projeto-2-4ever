package appconsole;

import regras_negocio.Fachada;

public class Teste2 {

	public static void main(String[] args) {

		// Dados para teste
		String cpf1 = "91";
		String cpf2 = "92";
		String cpf3 = "93";
		int id = 1;

		try {
			// Criando participates para realização dos testes abaixo
			Fachada.criarParticipante(cpf1, "01/01/2000");
			Fachada.criarParticipante(cpf2, "01/01/2000");

			// Criando convidado para realização dos testes abaixo
			Fachada.criarConvidado(cpf3, "01/01/2000", "xxxxx");

			// Criando evento para realização dos testes abaixo
			Fachada.criarEvento("01/01/2024", "teste", 2, 2);
		} catch (Exception e) {
			System.out.println("dados de teste--->" + e.getMessage());
		}

		System.out.println("\n-------TESTE DE EXCEÇÕES LANÇADAS PELOS MÉTODOS DA FACHADA--------");

		// Deve lançar exceção de participante duplicado
		try {
			Fachada.criarParticipante(cpf1, "01/01/2000");
			Fachada.criarParticipante(cpf1, "01/01/2000");
			System.out.println("*************1--->Não lançou exceção para: criar participante duplicado ");
		} catch (Exception e) {
			System.out.println("1ok--->" + e.getMessage());
		}

		// Deve lançar exceção ao criar um participante sem data
		try {
			Fachada.criarParticipante("1000", "");
			System.out.println("*************2--->Não lançou exceção para: criar participante sem data ");
		} catch (Exception e) {
			System.out.println("2ok--->" + e.getMessage());
		}

		// Deve lançar exceção ao criar um convidado sem empresa
		try {
			Fachada.criarConvidado("1001", "01/01/2000", "");
			System.out.println("*************3--->Não lançou exceção para: criar convidado sem empresa");
		} catch (Exception e) {
			System.out.println("3ok--->" + e.getMessage());
		}

		// Deve lançar exceção ao criar um evento sem data
		try {
			Fachada.criarEvento("", "teste", 10, 10);
			System.out.println("*************4--->Não lançou exceção para: criar evento sem data");
		} catch (Exception e) {
			System.out.println("4ok--->" + e.getMessage());
		}

		// Deve lançar exceção ao criar um evento sem descrição
		try {
			Fachada.criarEvento("01/01/2000", "", 10, 10);
			System.out.println("*************5--->Não lançou exceção para: criar evento sem descricao");
		} catch (Exception e) {
			System.out.println("5ok--->" + e.getMessage());
		}

		// Deve lançar exceção ao criar um evento com capacidade mínima menor do que 2
		try {
			Fachada.criarEvento("01/01/2000", "teste", 1, 10);
			System.out.println("*************6--->Não lançou exceção para: criar evento sem capacidade mínima");
		} catch (Exception e) {
			System.out.println("6ok--->" + e.getMessage());
		}

		// Deve lançar exceção ao criar um evento com preço negativo
		try {
			Fachada.criarEvento("01/01/2000", "teste", 2, -1);
			System.out.println("*************7--->Não lançou exceção para: criar evento com preco negativo");
		} catch (Exception e) {
			System.out.println("7ok--->" + e.getMessage());
		}

		// Deve lançar exceção ao criar um ingresso cujo id do evento não existe
		try {
			Fachada.criarIngresso(99, cpf1, "999999999");
			System.out.println("*************8--->Não lançou exceção para: criar ingresso com id inexistente");
		} catch (Exception e) {
			System.out.println("8ok--->" + e.getMessage());
		}

		// Deve lançar exceção ao criar ingresso com cpf inexistente
		try {
			Fachada.criarIngresso(id, "9999000", "999999999");
			System.out.println("*************9--->Não lançou exceção para: criar ingresso com cpf inexistente");
		} catch (Exception e) {
			System.out.println("9ok--->" + e.getMessage());
		}

		// Deve lançar exceção ao criar ingresso sem número de telefone
		try {
			Fachada.criarIngresso(id, cpf1, "");
			System.out.println("*************10--->Não lançou exceção para: criar ingresso com telefone inexistente");
		} catch (Exception e) {
			System.out.println("10ok--->" + e.getMessage());
		}

		// Deve lançar exceção ao criar um ingresso duplicado
		try {
			Fachada.criarIngresso(id, cpf1, "999999999");
			Fachada.criarIngresso(id, cpf1, "999999999");
			System.out.println("*************11--->Não lançou exceção para: criar ingresso duplicado");
		} catch (Exception e) {
			System.out.println("11ok--->" + e.getMessage());
		}

		// Deve lançar exceção ao tenter criar um ingresso num evento que já alcançou a
		// capacidade máxima
		try {
			Fachada.criarIngresso(id, cpf2, "999999999");
			Fachada.criarIngresso(id, cpf3, "999999999");
			System.out.println("*************12--->Não lançou exceção para: criar ingresso alem da capacidade");
		} catch (Exception e) {
			System.out.println("12ok--->" + e.getMessage());
		}

		// Deve lançar exceção ao tentar apagar um evento que ainda possui ingressos
		try {
			Fachada.apagarEvento(id);
			System.out.println("*************13--->Nao lançou exceção para: apagar evento ainda com ingresso");
		} catch (Exception e) {
			System.out.println("13ok--->" + e.getMessage());
		}

		// Deve lançar exceção ao tentar apagar um participante com ingresso em uso
		try {
			Fachada.apagarParticipante(cpf1);
			System.out.println("*************14--->Não lançou exceção para: apagar participante com ingresso em uso");
		} catch (Exception e) {
			System.out.println("14ok--->" + e.getMessage());
		}
	}
}