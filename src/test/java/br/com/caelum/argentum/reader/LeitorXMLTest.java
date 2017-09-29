package br.com.caelum.argentum.reader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmanegociacaoEmListaUnitaria() {
		String xmlDeTeste = "<list>" + 
							"	<negociacao>" + 
							"		<preco>43.5</preco>" + 
							"		<quantidade>1000</quantidade>" +
//							"		<data>2012-02-22T02:06:58</data>" +
							"		<data>" + 
							"			<time>1501113600000</time>" + 
							"			<timezone>Etc/UTC</timezone>" + 
							"		</data>" +
							"	</negociacao>" +
							"</list>";
		
		LeitorXML leitor = new LeitorXML();
		
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		Assert.assertEquals(1, negociacoes.size());
		Assert.assertEquals(new BigDecimal("43.5"), negociacoes.get(0).getPreco());
		Assert.assertEquals(1000, negociacoes.get(0).getQuantidade());
		Assert.assertEquals(22, negociacoes.get(0).getData().getDayOfMonth());
	}

}
