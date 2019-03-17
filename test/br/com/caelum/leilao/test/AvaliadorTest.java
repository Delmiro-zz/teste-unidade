package br.com.caelum.leilao.test;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.service.AvaliadorService;
import br.com.caelum.leilao.service.MatematicaService;

public class AvaliadorTest {
	
	private AvaliadorService leiloeiro;
	private Usuario maria;
	private Usuario joao;

	@Before
	public void criaAvalidor() {
		this.leiloeiro = new AvaliadorService();
		this.joao = new  Usuario("João");
		this.maria = new Usuario("Maria");
	}
	
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
		leiloeiro.avalia(leilao);
		
		//parte 3: validacao
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Console PS4");
		
		leilao.propoe(new Lance(joao, 1000.0));
		leiloeiro.avalia(leilao);
		
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadorDeLeilao().para("Console PS3")
				.lance(joao, 100.0)
				.lance(maria, 200.0)
				.lance(joao, 300.0)
				.lance(maria, 400.0).constroi();
		
		leiloeiro.avalia(leilao);
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, tresMaiores.size());
	}
	
	@Test
	public void deveMultiplicarNumerosMaioresQue30(){
		MatematicaService service = new MatematicaService();
		assertEquals(50*4, service.calculoSimples(50));
	}
	
	@Test
	public void deveMultiplicarNumerosMaioresQue10MenoresQue30(){
		MatematicaService service = new MatematicaService();
		assertEquals(20*3, service.calculoSimples(20));
	}
	
	@Test
	public void deveMultiplicarNumerosMenores10(){
		MatematicaService service = new MatematicaService();
		assertEquals(5*2, service.calculoSimples(5));
	}
	
}
