package repositorio;

import java.util.ArrayList;

import modelo.Evento;
import modelo.Participante;
import modelo.Ingresso;

public class Repositorio {
	private ArrayList<Evento> eventos = new ArrayList<>();
	private ArrayList<Participante> participantes = new ArrayList<>();
	private ArrayList<Ingresso> ingressos = new ArrayList<>();
	
	public void adicionar(Evento e) {
		eventos.add(e);
	}
	
	public void remover(Evento e) {
		eventos.remove(e);
	}
	
	public Evento localizarEvento(int id) {
		for(Evento e : eventos) {
			if(e.getId() == id) {
				return e;
			}
		}
		return null;
	}
	
	public void adicionar(Participante p) {
		participantes.add(p);
	}
	
	public void remover(Participante p) {
		participantes.remove(p);
	}
	
	public Participante localizarParticipante(String cpf) {
		for(Participante p : participantes) {
			if(p.getCpf().equals(cpf)) {
				return p;
			}
		}
		return null;
	}
	
	public void adicionar(Ingresso i) {
		ingressos.add(i);
	}
	
	public void remover(Ingresso i) {
		ingressos.remove(i);
	}
	
	public Ingresso localizarIngresso(String codigo) {
		for(Ingresso i : ingressos) {
			if(i.getCodigo().equals(codigo)) {
				return i;
			}
		}
		return null;
	}
	
	public ArrayList<Evento> getEventos() {
		return eventos;
	}
	
	public ArrayList<Participante> getParticipantes() {
		return participantes;
	}
	
	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}
	
	public int getTotalEventos() {
		return eventos.size();
	}
	
	public int getTotalParticipantes() {
		return participantes.size();
	}
	
	public int getTotalIngressos() {
		return ingressos.size();
	}
	
	public int gerarId() {
		if(eventos.isEmpty()) {
			return 1;
		}
		else {
			Evento ultimo = eventos.get(eventos.size() - 1);
			return ultimo.getId() + 1;
		}
	}
}
