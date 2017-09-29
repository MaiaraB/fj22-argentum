package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class Negociacao {
	private final BigDecimal preco;
	private final int quantidade;
	private final LocalDateTime data;
	
	public Negociacao(BigDecimal preco, int quantidade, LocalDateTime data) {
		if (data == null) {
			throw new IllegalArgumentException("data não pode ser nula");
		}
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public LocalDateTime getData() {
		return data;
	}
	
	public BigDecimal getVolume() {
		return preco.multiply(new BigDecimal(""+quantidade));
	}

	public boolean isMesmoDia(LocalDateTime mesmoMomento) {
		return this.data.toLocalDate().equals(mesmoMomento.toLocalDate());
	}
}
