package br.com.caelum.argentum.testes;

import java.util.List;

import br.com.caelum.argentum.modelo.Candlestick;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.ws.ClientWebService;

public class TestaClientWebService {

	public static void main(String[] args) {
		ClientWebService client = new ClientWebService();
		List<Negociacao> negociacoes = client.getNegociacoes();
		
		CandlestickFactory factory = new CandlestickFactory();
		List<Candlestick> candles = factory.constroiCandles(negociacoes);
		
		for (Candlestick candle : candles) {
			System.out.println(candle.toString());
		}
	}

}
