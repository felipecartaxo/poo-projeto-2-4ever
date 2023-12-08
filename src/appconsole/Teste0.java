package appconsole;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;

public class Teste0 {

	public static void main(String[] args) {
		// Testando o método calcularIdade()
		Participante p1 = new Participante("123", "16/06/1995");
		System.out.println(p1.calcularIdade());
		
		Convidado c1 = new Convidado("890", "20/08/2000", "ifpb");
		System.out.println("Idade do convidado: " + c1.calcularIdade());
		System.out.println(c1.getIngressos());
		
		// Testando o método lotado() e quantidadeIngressos()
		Evento e1 = new Evento(1, "01/12/2024", "Show da Doja Cat", 200, 1500);
		System.out.println(e1.lotado());
		System.out.println(e1.quantidadeIngressos());
		
		// Testando o método calcularPreco
		Ingresso i1 = new Ingresso("1", "Show da Rihanna");
		
		System.out.println(p1);
		System.out.println(c1);
		System.out.println(e1);
		
		System.out.println(e1.getCapacidade());
		System.out.println(e1.quantidadeIngressos());
		System.out.println(e1.totalArrecadado());
	}
}