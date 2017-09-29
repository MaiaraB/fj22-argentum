package br.com.caelum.argentum.ws;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.reader.LeitorXML;

public class ClientWebService {

	private static final String URL_WEBSERVICE = "http://argentumws.caelum.com.br/negociacoes";
	
	public List<Negociacao> getNegociacoes() {
		HttpURLConnection connection = null;
		
		try {
			URL url = new URL(URL_WEBSERVICE);
			//InputStream is = url.openStream();
			
			connection = (HttpURLConnection)url.openConnection();
			InputStream is = connection.getInputStream();
			
			LeitorXML leitor = new LeitorXML();
			return leitor.carrega(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
	}
}
