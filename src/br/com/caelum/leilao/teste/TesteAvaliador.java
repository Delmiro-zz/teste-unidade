package br.com.caelum.leilao.teste;


import org.junit.Assert;
import org.junit.Test;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.service.Avaliador;

public class TesteAvaliador {
	
	@Test
	public void deveEntenderLancesEmOrdemCrescenteTest() {
		//parti 1 : cenario
		Usuario maradona = new Usuario("Maradona");
		Usuario pele = new Usuario("Pelé");
		Usuario zidane = new Usuario("Zidane");
		Leilao leilao = new Leilao("Console PS4");
		leilao.propoe(new Lance(maradona, 250.0));
		leilao.propoe(new Lance(pele, 300.0));
		leilao.propoe(new Lance(zidane, 400.0));
		
		//parte 2: acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//parte 3: validacao
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);
	}
}
