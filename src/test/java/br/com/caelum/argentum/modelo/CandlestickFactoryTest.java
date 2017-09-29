package br.com.caelum.argentum.modelo;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CandlestickFactoryTest {

	@Test
	public void sequenciaSimplesDeNegociacoes() {
		LocalDateTime hoje = LocalDateTime.now();
		
		Negociacao negociacao1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		Negociacao negociacao2 = new Negociacao(new BigDecimal("45.0"), 100, hoje);
		Negociacao negociacao3 = new Negociacao(new BigDecimal("39.8"), 100, hoje);
		Negociacao negociacao4 = new Negociacao(new BigDecimal("42.3"), 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		Assert.assertEquals(new BigDecimal("40.5"), candle.getAbertura());
		Assert.assertEquals(new BigDecimal("42.3"), candle.getFechamento());
		Assert.assertEquals(new BigDecimal("39.8"), candle.getMinimo());
		Assert.assertEquals(new BigDecimal("45.0"), candle.getMaximo());
		Assert.assertEquals(new BigDecimal("167.6"), candle.getVolume());
	}
	
	@Test
	public void semNegociacoesGeraCandleComZeros() {
		LocalDateTime hoje = LocalDateTime.now();
		
		List<Negociacao> negociacoes = Arrays.asList();
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		Assert.assertEquals(new BigDecimal("0"), candle.getAbertura());
		Assert.assertEquals(new BigDecimal("0"), candle.getFechamento());
		Assert.assertEquals(new BigDecimal("0"), candle.getMinimo());
		Assert.assertEquals(new BigDecimal("0"), candle.getMaximo());
		Assert.assertEquals(new BigDecimal("0"), candle.getVolume());
	}
	
	@Test
	public void apenasUmaNegociacaoGeraCandleComValoresIguais() {
		LocalDateTime hoje = LocalDateTime.now();
		
		Negociacao negociacao1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		Assert.assertEquals(new BigDecimal("40.5"), candle.getAbertura());
		Assert.assertEquals(new BigDecimal("40.5"), candle.getFechamento());
		Assert.assertEquals(new BigDecimal("40.5"), candle.getMinimo());
		Assert.assertEquals(new BigDecimal("40.5"), candle.getMaximo());
		Assert.assertEquals(new BigDecimal("40.5"), candle.getVolume());
		
	}
	
	@Test
	public void negociacoesEmOrdemCrescenteDeValor() {
		LocalDateTime hoje = LocalDateTime.now();
		
		Negociacao negociacao1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		Negociacao negociacao2 = new Negociacao(new BigDecimal("45.0"), 100, hoje);
		Negociacao negociacao3 = new Negociacao(new BigDecimal("46.9"), 100, hoje);
		Negociacao negociacao4 = new Negociacao(new BigDecimal("51.2"), 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		Assert.assertEquals(new BigDecimal("40.5"), candle.getAbertura());
		Assert.assertEquals(new BigDecimal("51.2"), candle.getFechamento());
		Assert.assertEquals(new BigDecimal("40.5"), candle.getMinimo());
		Assert.assertEquals(new BigDecimal("51.2"), candle.getMaximo());
		Assert.assertEquals(new BigDecimal("183.6"), candle.getVolume());
	}
	
	@Test
	public void negociacoesEmOrdemDecrescenteDeValor() {
		LocalDateTime hoje = LocalDateTime.now();
		
		Negociacao negociacao1 = new Negociacao(new BigDecimal("51.2"), 100, hoje);
		Negociacao negociacao2 = new Negociacao(new BigDecimal("46.9"), 100, hoje);
		Negociacao negociacao3 = new Negociacao(new BigDecimal("45.0"), 100, hoje);
		Negociacao negociacao4 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		Assert.assertEquals(new BigDecimal("51.2"), candle.getAbertura());
		Assert.assertEquals(new BigDecimal("40.5"), candle.getFechamento());
		Assert.assertEquals(new BigDecimal("40.5"), candle.getMinimo());
		Assert.assertEquals(new BigDecimal("51.2"), candle.getMaximo());
		Assert.assertEquals(new BigDecimal("183.6"), candle.getVolume());
	}
	
	@Test
	public void paraNegociacoesDeTresDiasDistintosGeraTresCandles() {
		LocalDateTime hoje = LocalDateTime.now();
		
		Negociacao negociacao1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		Negociacao negociacao2 = new Negociacao(new BigDecimal("45.0"), 100, hoje);
		Negociacao negociacao3 = new Negociacao(new BigDecimal("39.8"), 100, hoje);
		Negociacao negociacao4 = new Negociacao(new BigDecimal("42.3"), 100, hoje);
		
		LocalDateTime amanha = hoje.withDayOfMonth(hoje.getDayOfMonth()+1);
		
		Negociacao negociacao5 = new Negociacao(new BigDecimal("48.8"), 100, amanha);
		Negociacao negociacao6 = new Negociacao(new BigDecimal("49.3"), 100, amanha);
		
		LocalDateTime depois = amanha.withDayOfMonth(amanha.getDayOfMonth()+1);
		
		Negociacao negociacao7 = new Negociacao(new BigDecimal("51.8"), 100, depois);
		Negociacao negociacao8 = new Negociacao(new BigDecimal("52.3"), 100, depois);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3,
				negociacao4, negociacao5, negociacao6, negociacao7, negociacao8);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		
		List<Candlestick> candles = fabrica.constroiCandles(negociacoes);
		
		Assert.assertEquals(3, candles.size());
		Assert.assertEquals(new BigDecimal("40.5"), candles.get(0).getAbertura());
		Assert.assertEquals(new BigDecimal("42.3"), candles.get(0).getFechamento());
		Assert.assertEquals(new BigDecimal("48.8"), candles.get(1).getAbertura());
		Assert.assertEquals(new BigDecimal("49.3"), candles.get(1).getFechamento());
		Assert.assertEquals(new BigDecimal("51.8"), candles.get(2).getAbertura());
		Assert.assertEquals(new BigDecimal("52.3"), candles.get(2).getFechamento());		
	}
	
	@Test
	public void naoPermiteConstruirCandlesComNegociacoesForaDeOrdem() {
		LocalDateTime hoje = LocalDateTime.now();
		LocalDateTime amanha = hoje.plusDays(1);
		LocalDateTime depois = amanha.plusDays(1);
		
		Negociacao negociacao1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		Negociacao negociacao2 = new Negociacao(new BigDecimal("45.0"), 100, hoje);
		Negociacao negociacao3 = new Negociacao(new BigDecimal("39.8"), 100, hoje);
		Negociacao negociacao4 = new Negociacao(new BigDecimal("42.3"), 100, hoje);
		Negociacao negociacao5 = new Negociacao(new BigDecimal("48.8"), 100, amanha);
		Negociacao negociacao6 = new Negociacao(new BigDecimal("49.3"), 100, amanha);
		Negociacao negociacao7 = new Negociacao(new BigDecimal("51.8"), 100, depois);
		Negociacao negociacao8 = new Negociacao(new BigDecimal("52.3"), 100, depois);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao5, negociacao7,
				negociacao2, negociacao8, negociacao6, negociacao3, negociacao4);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		
		List<Candlestick> candles = fabrica.constroiCandles(negociacoes);
		
		Assert.assertEquals(3, candles.size());
		Assert.assertEquals(new BigDecimal("40.5"), candles.get(0).getAbertura());
		Assert.assertEquals(new BigDecimal("42.3"), candles.get(0).getFechamento());
		Assert.assertEquals(new BigDecimal("48.8"), candles.get(1).getAbertura());
		Assert.assertEquals(new BigDecimal("49.3"), candles.get(1).getFechamento());
		Assert.assertEquals(new BigDecimal("51.8"), candles.get(2).getAbertura());
		Assert.assertEquals(new BigDecimal("52.3"), candles.get(2).getFechamento());
	}

}
