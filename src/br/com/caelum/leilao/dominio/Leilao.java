package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		int totalDeLance = 0;
		
		for (Lance lanceDado : lances) {
			if(lanceDado.getUsuario().equals(lance.getUsuario()))
				totalDeLance++;
		}
		
		if(lances.isEmpty() || (!ultimoUsuarioLanceDado().getUsuario().equals(lance.getUsuario()) && totalDeLance < 5)) {
			lances.add(lance);
		}
	}

	private Lance ultimoUsuarioLanceDado() {
		return lances.get(lances.size()-1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	
	
}
