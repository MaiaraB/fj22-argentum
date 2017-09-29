package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CandlestickFactory {
	public Candlestick constroiCandleParaData(LocalDateTime data, List<Negociacao> negociacoes){
		BigDecimal abertura = negociacoes.isEmpty() ? new BigDecimal("0") : negociacoes.get(0).getPreco();
		BigDecimal fechamento = negociacoes.isEmpty() ? new BigDecimal("0") : 
									negociacoes.get(negociacoes.size()-1).getPreco();
		BigDecimal minimo = new BigDecimal(""+Double.MAX_VALUE);
		BigDecimal maximo = new BigDecimal("0");
		BigDecimal volume = new BigDecimal("0");
		
		for (Negociacao negociacao : negociacoes) {
			if (minimo.compareTo(negociacao.getPreco()) > 0) {
				minimo = negociacao.getPreco();
			} 
			if (maximo.compareTo(negociacao.getPreco()) < 0) {
				maximo = negociacao.getPreco();
			}
			volume = volume.add(negociacao.getPreco());
		}
		
//		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
		
		if (minimo.compareTo(maximo) > 0) {
			minimo = maximo;
		}
		
		CandleBuilder builder = new CandleBuilder();
		builder.comAbertura(abertura)
				.comFechamento(fechamento)
				.comMinimo(minimo)
				.comMaximo(maximo)
				.comVolume(volume)
				.comData(data);
		
		return builder.geraCandle();
	}
	
	public List<Candlestick> constroiCandles(List<Negociacao> negociacoes) {
		if (negociacoes.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<Candlestick> candles = new ArrayList<>();
		
		Map<LocalDateTime, List<Negociacao>> agrupadasPorData = negociacoes.stream().collect(Collectors.groupingBy(Negociacao::getData));
		agrupadasPorData.forEach((data, negociacoesDaData) -> candles.add(constroiCandleParaData(data, negociacoesDaData)));
		
		candles.sort((c1, c2) -> c1.getData().compareTo(c2.getData()));
		
		return candles;
	}

//	public List<Candlestick> constroiCandles(List<Negociacao> negociacoes) {
//		Collections.sort(negociacoes, (a, b) -> 
//										a.getData().toLocalDate().compareTo(b.getData().toLocalDate()));
//		List<Candlestick> candles = new ArrayList<Candlestick>();
//		
//		List<Negociacao> negociacoesDoDia = new ArrayList<Negociacao>();
//		LocalDateTime dataAtual = negociacoes.get(0).getData();
//		
//		for (Negociacao negociacao : negociacoes) {
////			if (negociacao.getData().isBefore(dataAtual)) {
////				throw new IllegalStateException("negociacoes em ordem errada");
////			}
//			if(!negociacao.isMesmoDia(dataAtual)) {
//				Candlestick candleDoDia = this.constroiCandleParaData(dataAtual, negociacoesDoDia);
//				candles.add(candleDoDia);
//				dataAtual = negociacao.getData();
//				negociacoesDoDia = new ArrayList<Negociacao>();
//			}
//			negociacoesDoDia.add(negociacao);
//		}
//		Candlestick candleDoDia = this.constroiCandleParaData(dataAtual, negociacoesDoDia);
//		candles.add(candleDoDia);
//		
//		return candles;
//
//	}
	
}
