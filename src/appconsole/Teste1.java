package appconsole;

import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import regras_negocio.Fachada;

public class Teste1 {

	public Teste1() {

		try {
			System.out.println("\ncriando participantes e convidados");

			// Criando os participantes
			Fachada.criarParticipante("1111", "01/01/1960");
			Fachada.criarParticipante("2222", "01/01/2000");
			Fachada.criarParticipante("3333", "01/01/2010");

			// Criando os convidados
			Fachada.criarConvidado("4444", "01/01/1960", "empresa1");
			Fachada.criarConvidado("5555", "01/01/2000", "empresa2");
			Fachada.criarConvidado("6666", "01/01/2010", "empresa3");
			Fachada.criarConvidado("7777", "01/01/2010", "empresa3");

			System.out.println("---------listagem de participantes e convidados -----");

			// Listando os participantes
			for (Participante p : Fachada.listarParticipantes())
				System.out.println(p);

		} catch (Exception e) {
			System.out.println("--->" + e.getMessage());
		}

		try {
			System.out.println("\ncriando eventos");

			// Criando os eventos
			Fachada.criarEvento("24/11/2023", "show da pisadinha", 2, 100.0);
			Fachada.criarEvento("24/06/2024", "show de sao joao", 2, 200.0);
			Fachada.criarEvento("01/01/2024", "fake", 2, 0.0);

			System.out.println("---------listagem de eventos");

			// Listando os eventos
			for (Evento e : Fachada.listarEventos())
				System.out.println(e);

		} catch (Exception e) {
			System.out.println("--->" + e.getMessage());
		}

		try {
			System.out.println("\ncriando ingressos");

			// Criando os ingressos
			Fachada.criarIngresso(1, "1111", "988001101");
			Fachada.criarIngresso(1, "2222", "988001102");
			Fachada.criarIngresso(1, "3333", "988001103");
			Fachada.criarIngresso(1, "4444", "988001104");
			Fachada.criarIngresso(1, "5555", "988001105");
			Fachada.criarIngresso(1, "6666", "988001106");
			Fachada.criarIngresso(2, "1111", "988001101");
			Fachada.criarIngresso(2, "2222", "988001102");
			Fachada.criarIngresso(2, "3333", "988001103");
			Fachada.criarIngresso(2, "4444", "988001104");
			Fachada.criarIngresso(2, "5555", "988001105");
			Fachada.criarIngresso(2, "6666", "988001106");
			Fachada.criarIngresso(1, "7777", "988001107");

			System.out.println("---------listagem de ingressos");

			// Listando os ingressos
			for (Ingresso ing : Fachada.listarIngressos())
				System.out.println(
						"cod=" + ing.getCodigo() + ", " + ing.getTelefone() + ", preco=" + ing.calcularPreco() +
								", evento preco=" + ing.getEvento().getPreco() + ", arrecadado="
								+ ing.getEvento().totalArrecadado() +
								", idade=" + ing.getParticipante().calcularIdade());

		} catch (Exception e) {
			System.out.println("--->" + e.getMessage());
		}

		try {
			System.out.println("\napagando evento");

			// Apagando o evento de id 3
			Fachada.apagarEvento(3);

			// Apagando o participante de cpf "777"
			System.out.println("\napagando participante");
			Fachada.apagarParticipante("7777");

			// Apagando ingressos a partir do código dos mesmos
			System.out.println("\napagando ingresssos");
			Fachada.apagarIngresso("1-1111");
			Fachada.apagarIngresso("2-6666");

		} catch (Exception e) {
			System.out.println("--->" + e.getMessage());
		}

		try {
			System.out.println("\n---------listagem de participantes - final-----");

			// Listando os participantes após as inserções/remoções
			for (Participante p : Fachada.listarParticipantes())
				System.out.println(p);

			System.out.println("\n---------listagem de eventos - final");

			// Listando os eventos após as inserções/remoções
			for (Evento e : Fachada.listarEventos())
				System.out.println(e);

			System.out.println("\n---------listagem de ingressos - final");

			// Listando os ingressos após as inserções/remoções
			for (Ingresso ing : Fachada.listarIngressos())
				System.out.println(
						"cod=" + ing.getCodigo() + ", " + ing.getTelefone() + ", preco=" + ing.calcularPreco() +
								", evento preco=" + ing.getEvento().getPreco() + ", arrecadado="
								+ ing.getEvento().totalArrecadado() +
								", idade=" + ing.getParticipante().calcularIdade());

		} catch (Exception e) {
			System.out.println("--->" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Teste1(); // Executando o teste1
	}
}