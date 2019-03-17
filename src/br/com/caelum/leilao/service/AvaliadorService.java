package br.com.caelum.leilao.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class AvaliadorService {

	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;
	private List<Lance> maiores;

	public void avalia(Leilao leilao) {
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorLance) {
				maiorLance = lance.getValor();
			}  
			if (lance.getValor() < menorLance) {
				menorLance = lance.getValor();
			}
		}
		
		maiores = new ArrayList<Lance>(leilao.getLances());
		maiores.sort(Comparator.comparing(Lance::getValor));
		maiores = maiores.subList(0, maiores.size() > 3 ? 3: maiores.size());
	}

	public List<Lance> getTresMaiores() {
		return maiores;
	}
	
	public double getMaiorLance() {
		return maiorLance;
	}

	public double getMenorLance() {
		return menorLance;
	}

}
