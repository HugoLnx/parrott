package br.com.parrot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

import br.com.caelum.vraptor.ioc.Component;
import br.com.parrot.exceptions.HttpNotFoundException;

@Component
public class GetRequest {

	public String responseBody(URI uri) throws ClientProtocolException, IOException, HttpNotFoundException {
		HttpResponse response = Request.Get(uri).execute().returnResponse();
		int code = response.getStatusLine().getStatusCode();

		if(code == 404) {
			throw new HttpNotFoundException();
		}
		
		InputStream bodyStream = response.getEntity().getContent();
		String body = readStream(bodyStream);
		
		return body;
	}

	private String readStream(InputStream stream) {
		StringBuilder str = new StringBuilder();
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
		
			while(reader.ready()) {
				str.append((char) reader.read());
			}
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException("Erro ao ler o Stream");
		}
		
		return str.toString();
	}
}
