package br.com.parrot.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.parrot.github.model.Payload;
import br.com.parrot.github.repository.PayloadsParser;

@Resource
@Path("/timeline")
public class TimeLineController {
	
	private static final int FILES_LIMIT = 10;
	private final Result result; 
	private final PayloadsParser pushevent;
	
	public TimeLineController(Result result, PayloadsParser pushevents){
		this.pushevent = pushevents;
		this.result = result;
	}

	@Get("/")
	public void redirectToTimeline(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		result.redirectTo(this).showTimeLine(username);
	}
	
	@Get("/{username}")
	public void showTimeLine(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		List<Payload> payloads = pushevent.findPayloadsOf(username, FILES_LIMIT);
		
		result.include("username", username);
		result.include("payloads",payloads);
	}
	
}
