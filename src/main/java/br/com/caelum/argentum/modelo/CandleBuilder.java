package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CandleBuilder {
	
	private BigDecimal abertura;
	private BigDecimal fechamento;
	private BigDecimal minimo;
	private BigDecimal maximo;
	private BigDecimal volume;
	private LocalDateTime data;
	private boolean aberturaSetted = false;
	private boolean fechamentoSetted = false;
	private boolean minimoSetted = false;
	private boolean maximoSetted = false;
	private boolean volumeSetted = false;
	private boolean dataSetted = false;
	
	public CandleBuilder comAbertura(BigDecimal abertura) {
		this.abertura  = abertura;
		aberturaSetted = true;
		return this;
	}
	
	public CandleBuilder comFechamento(BigDecimal fechamento) {
		this.fechamento  = fechamento;
		fechamentoSetted = true;
		return this;
	}
	
	public CandleBuilder comMinimo(BigDecimal minimo) {
		this.minimo  = minimo;
		minimoSetted = true;
		return this;
	}
	
	public CandleBuilder comMaximo(BigDecimal maximo) {
		this.maximo  = maximo;
		maximoSetted = true;
		return this;
	}
	
	public CandleBuilder comVolume(BigDecimal volume) {
		this.volume  = volume;
		volumeSetted = true;
		return this;
	}
	
	public CandleBuilder comData(LocalDateTime data) {
		this.data  = data;
		dataSetted = true;
		return this;
	}
	
	public Candlestick geraCandle() {
		if (!(aberturaSetted && fechamentoSetted && volumeSetted && minimoSetted && maximoSetted && dataSetted)) {
			throw new IllegalStateException("faltam valores");
		}
		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
	}
	
}
