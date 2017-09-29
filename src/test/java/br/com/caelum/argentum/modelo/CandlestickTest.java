package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

public class CandlestickTest {

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		new Candlestick(new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("20"), 
				new BigDecimal("10"), new BigDecimal("10000"), LocalDateTime.now());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoPodeTerDataNula() {
		new Candlestick(new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("20"), 
				new BigDecimal("20"), new BigDecimal("10000"), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoPodeTerAberturaNegativa() {
		new Candlestick(new BigDecimal("-10"), new BigDecimal("20"), new BigDecimal("20"), 
				new BigDecimal("20"), new BigDecimal("10000"), LocalDateTime.now());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoPodeTerFechamentoNegativo() {
		new Candlestick(new BigDecimal("10"), new BigDecimal("-20"), new BigDecimal("20"), 
				new BigDecimal("20"), new BigDecimal("10000"), LocalDateTime.now());
	}
	
	@Test
	public void quandoAberturaIgualFechamentoIEhAlta() {
		Candlestick candle = new Candlestick(new BigDecimal("10"), new BigDecimal("10"), new BigDecimal("20"), 
				new BigDecimal("20"), new BigDecimal("10000"), LocalDateTime.now());
		
		Assert.assertEquals(true, candle.isAlta());
	}

}
