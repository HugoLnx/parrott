package br.com.parrot;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class GetRequest {

	public String responseBody(URI uri) throws HttpResponseException {
		try {
			return Request.Get(uri).execute().returnContent().asString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new RuntimeException("ClientProtocolException on GetRequest");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IOException on GetRequest");
		}
	}

}
