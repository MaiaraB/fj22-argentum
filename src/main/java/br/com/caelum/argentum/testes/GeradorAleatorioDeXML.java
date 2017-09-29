package br.com.caelum.argentum.testes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.reader.LeitorXML;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GeradorAleatorioDeXML {
	
	public static void main(String[] args) throws FileNotFoundException {
		LocalDateTime data = LocalDateTime.now();
		Random random = new Random(123);
		List<Negociacao> negociacoes = new ArrayList<Negociacao>();
		
		BigDecimal valor = new BigDecimal("40");
		int quantidade = 1000;
		
		for (int dias = 0; dias < 30; dias++) {
			int quantidadeDeNegociacoesDoDia = random.nextInt(20);
			
			for (int negociacao=0; negociacao < quantidadeDeNegociacoesDoDia; negociacao++) {
				valor = valor.add(new BigDecimal(""+(random.nextInt(200) - 100)/100.0));
				if (valor.compareTo(new BigDecimal("5.0")) < 0) {
					valor = new BigDecimal("5.0");
				}
				
				quantidade += 1000 - random.nextInt(500);
				
				Negociacao n = new Negociacao(valor, quantidade, data);
				negociacoes.add(n);
			}
			
			data = data.plusDays(1);
		}
		
		XStream stream = new XStream(new DomDriver());
		stream.alias("negociacao", Negociacao.class);
		//stream.setMode(XStream.NO_REFERENCES);
		
		PrintStream out = new PrintStream(new File("negociacao.xml"));
		out.println(stream.toXML(negociacoes));
		
	}
	
}
