package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;

import org.junit.Test;

public class CandleBuilderTest {

	@Test(expected=IllegalStateException.class)
	public void geracaoDeCandleDeveTerTodosOsDadosNecessarios() {
		CandleBuilder builder = new CandleBuilder();
		builder.comAbertura(new BigDecimal("10"))
			.comMinimo(new BigDecimal("5"));
		
		builder.geraCandle();
	}

}
