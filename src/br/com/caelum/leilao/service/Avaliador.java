package br.com.caelum.leilao.service;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;

	public void avalia(Leilao leilao) {
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorDeTodos) {
				maiorDeTodos = lance.getValor();
			}  if (lance.getValor() < menorDeTodos) {
				menorDeTodos = lance.getValor();
			}
		}
	}

	public double getMaiorDeTodos() {
		return maiorDeTodos;
	}

	public double getMenorDeTodos() {
		return menorDeTodos;
	}

}
