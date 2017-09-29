package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Candlestick {
	private final BigDecimal abertura;
	private final BigDecimal fechamento;
	private final BigDecimal minimo;
	private final BigDecimal maximo;
	private final BigDecimal volume;
	private final LocalDateTime data;
	
	public Candlestick(BigDecimal abertura, BigDecimal fechamento,
			BigDecimal minimo, BigDecimal maximo, BigDecimal volume,
			LocalDateTime data) {
		if (minimo.compareTo(maximo) > 0) {
			throw new IllegalArgumentException("minimo não pode ser maior que o maximo");
		}
		if (data == null) {
			throw new IllegalArgumentException("data não pode ser nula");
		}
		BigDecimal zero = new BigDecimal("0");
		if (abertura.compareTo(zero) < 0 || fechamento.compareTo(zero) < 0 || minimo.compareTo(zero) < 0
				|| maximo.compareTo(zero) < 0 || volume.compareTo(zero) < 0) {
			throw new IllegalArgumentException("valores não podem ser negativos");
		}
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	public BigDecimal getAbertura() {
		return abertura;
	}

	public BigDecimal getFechamento() {
		return fechamento;
	}

	public BigDecimal getMinimo() {
		return minimo;
	}

	public BigDecimal getMaximo() {
		return maximo;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public LocalDateTime getData() {
		return data;
	}
	
	public boolean isAlta() {
		return this.abertura.compareTo(this.fechamento) <= 0;
	}
	
	public boolean isBaixa() {
		return this.abertura.compareTo(this.fechamento) > 0;
	}
	
	@Override
	public String toString() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		StringBuilder stringFinal = new StringBuilder();
		stringFinal.append("[Abertura " + this.abertura)
					.append(", Fechamento " + this.fechamento)
					.append(", Mínima  " + this.minimo)
					.append(", Máxima " + this.maximo)
					.append(",\nVolume " + this.volume)
					.append(", Data " + this.data.format(formatador));
		
		return stringFinal.toString();
	}
}
