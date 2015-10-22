package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	
	@Test
	public void deveCriarUmaUnicaSessaoQuandoForOMesmoDia () {
		Espetaculo depecheMode  = new Espetaculo ();
		LocalDate inicio = new LocalDate ( 2015, 10, 20 );
		LocalDate fim = new LocalDate ( 2015, 10, 20 );
		LocalTime horario = new LocalTime ( 21, 00 );
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		
		
		List<Sessao> sessoes = depecheMode.criaSessoes(inicio, fim, horario, periodicidade );
		
		assertTrue( sessoes != null );
		assertTrue( sessoes.isEmpty() == false );
		
		Integer days = ( Days.daysBetween(inicio, fim).getDays() );
		days /= periodicidade.equals(Periodicidade.DIARIA) ? 1 : 7;
		days ++;
		assertTrue ( days.equals(sessoes.size()));
	}

	@Test
	public void deveCriarUmaUnicaSessaoSemanalMesmoDia () {
		Espetaculo depecheMode  = new Espetaculo ();
		LocalDate inicio = new LocalDate ( 2015, 10, 20 );
		LocalDate fim = new LocalDate ( 2015, 10, 20 );
		LocalTime horario = new LocalTime ( 21, 00 );
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		
		
		
		List<Sessao> sessoes = depecheMode.criaSessoes(inicio, fim, horario, periodicidade );
		
		assertTrue( sessoes != null );
		assertTrue( sessoes.isEmpty() == false );
		
		Integer days = ( Days.daysBetween(inicio, fim).getDays() );
		days /= periodicidade.equals(Periodicidade.DIARIA) ? 1 : 7;
		days ++;
		assertTrue ( days.equals(sessoes.size()));
	}
	
	@Test
	public void deveCriarUmaUnicaSessaoDiariaParaOMes () {
		Espetaculo depecheMode  = new Espetaculo ();
		LocalDate inicio = new LocalDate ( 2015, 10, 20 );
		LocalDate fim = new LocalDate ( 2015, 11, 20 );
		LocalTime horario = new LocalTime ( 21, 00 );
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		
		
		List<Sessao> sessoes = depecheMode.criaSessoes(inicio, fim, horario, periodicidade );
		
		assertTrue( sessoes != null );
		assertTrue( sessoes.isEmpty() == false );
		
		Integer days = ( Days.daysBetween(inicio, fim).getDays() );
		days /= periodicidade.equals(Periodicidade.DIARIA) ? 1 : 7;
		days ++;
		assertTrue ( days.equals(sessoes.size()));
	}

	@Test
	public void deveCriarUmaUnicaSessaoSeemanalParaOMes () {
		Espetaculo depecheMode  = new Espetaculo ();
		LocalDate inicio = new LocalDate ( 2015, 10, 20 );
		LocalDate fim = new LocalDate ( 2015, 11, 20 );
		LocalTime horario = new LocalTime ( 21, 00 );
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		
		
		
		List<Sessao> sessoes = depecheMode.criaSessoes(inicio, fim, horario, periodicidade );
		
		assertTrue( sessoes != null );
		assertTrue( sessoes.isEmpty() == false );
		
		Integer days = ( Days.daysBetween(inicio, fim).getDays() );
		days /= periodicidade.equals(Periodicidade.DIARIA) ? 1 : 7;
		days ++;
		assertTrue ( days.equals(sessoes.size()));
	}
}
