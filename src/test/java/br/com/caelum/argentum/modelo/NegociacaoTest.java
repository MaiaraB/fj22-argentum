package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

public class NegociacaoTest {

	@Test (expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(new BigDecimal("10"), 5, null);
	}
	
	@Test
	public void mesmoMilisegundoEhDoMesmoDia() {
		LocalDateTime agora = LocalDateTime.now();
		LocalDateTime mesmoMomento = agora;
		
		Negociacao negociacao = new Negociacao(new BigDecimal("40.0"), 100, agora);
		
		Assert.assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}
	
	@Test
	public void comHorariosDiferentesEhNoMesmoDia() {
		LocalDateTime manha = LocalDateTime.of(2017, 9, 19, 9, 30);
		LocalDateTime tarde = LocalDateTime.of(2017, 9, 19, 15, 30);
		
		Negociacao negociacao = new Negociacao(new BigDecimal("40.0"), 100, manha);
		
		Assert.assertTrue(negociacao.isMesmoDia(tarde));
	}
	
	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoMesmoDia() {
		LocalDateTime diaMes1 = LocalDateTime.of(2017, 9, 19, 9, 30);
		LocalDateTime diaMes2 = LocalDateTime.of(2017, 10, 19, 9, 30);
		
		Negociacao negociacao = new Negociacao(new BigDecimal("40.0"), 100, diaMes1);
		
		Assert.assertFalse(negociacao.isMesmoDia(diaMes2));
	}
	
	@Test
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoMesmoDia() {
		LocalDateTime diaAno1 = LocalDateTime.of(2017, 9, 19, 9, 30);
		LocalDateTime diaAno2 = LocalDateTime.of(2016, 9, 19, 9, 30);
		
		Negociacao negociacao = new Negociacao(new BigDecimal("40.0"), 100, diaAno1);
		
		Assert.assertFalse(negociacao.isMesmoDia(diaAno2));
	}
}
