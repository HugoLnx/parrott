package br.com.parrot.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.parrot.github.repository.Subscribe;

@Resource
@Path("/subscribe")
public class SubscribeController {
	
	private final Result result;
	private final Subscribe subscribe;
	
	
	public SubscribeController(Result result, Subscribe subscribe){
		this.result = result;
		this.subscribe = subscribe;
	}

	@Get("/")
	public void redirectToRegistra() throws ClientProtocolException, JSONException, IOException, URISyntaxException {
		result.redirectTo("/");
	}
	
	@Post("/")
	public void registra(String email) throws ClientProtocolException, JSONException, IOException, URISyntaxException{
		this.subscribe.writeEmailOnTxt(email);
		result.include("mensagem", "Seu email foi registrado com sucesso!");
		result.use(Results.page()).forwardTo("/index.jsp");
	}
	
}
