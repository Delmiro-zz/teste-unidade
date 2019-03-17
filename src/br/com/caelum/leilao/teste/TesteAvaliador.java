package br.com.caelum.leilao.teste;


import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.service.AvaliadorService;
import br.com.caelum.leilao.service.MatematicaService;

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
		AvaliadorService leiloeiro = new AvaliadorService();
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
		AvaliadorService leiloeiro = new AvaliadorService();
		leiloeiro.avalia(leilao);
		
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Usuario joao = new  Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Console PS3");
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		AvaliadorService avaliador = new AvaliadorService();
		avaliador.avalia(leilao);
		List<Lance> tresMaiores = avaliador.getTresMaiores();
		
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
