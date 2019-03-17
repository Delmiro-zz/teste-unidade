package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTest {
	
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("MacBook Pro 15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Diego"), 2000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLanches() {
		Leilao leilao = new Leilao("MacBook Pro 15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Diego"), 2000));
		leilao.propoe(new Lance(new Usuario("Fulano"), 3000));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLanceSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("MacBook Pro 15");
		Usuario diego = new Usuario("Diego");
		
		leilao.propoe(new Lance(diego, 2000.0));
		leilao.propoe(new Lance(diego, 3000.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarCincoLancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("MacBook Pro 15");
		Usuario diego = new Usuario("Diego");
		Usuario fulano = new Usuario("Fulano");
		
		leilao.propoe(new Lance(diego, 2000.0));
		leilao.propoe(new Lance(fulano, 3000.0));
		
		leilao.propoe(new Lance(diego, 4000.0));
		leilao.propoe(new Lance(fulano, 5000.0));
		
		leilao.propoe(new Lance(diego, 6000.0));
		leilao.propoe(new Lance(fulano, 7000.0));
		
		leilao.propoe(new Lance(diego, 8000.0));
		leilao.propoe(new Lance(fulano, 9000.0));
		
		leilao.propoe(new Lance(diego, 10000.0));
		leilao.propoe(new Lance(fulano, 11000.0));
		
		//rejeita
		leilao.propoe(new Lance(diego, 12000.0));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
	
}
