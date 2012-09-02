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
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.Payload;
import br.com.parrot.github.repository.PayloadsFinder;

@Resource
@Path("/timeline")
public class TimeLineController {
	
	private static final int MAX_PAYLOADS = 5;
	private final Result result; 
	private final PayloadsFinder pushevent;
	private final CommitFilesLoader commitFilesLoader;
	
	public TimeLineController(Result result, PayloadsFinder pushevents, CommitFilesLoader commitFilesLoader){
		this.pushevent = pushevents;
		this.result = result;
		this.commitFilesLoader = commitFilesLoader;
	}

	@Get("/")
	public void redirectToTimeline(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		result.redirectTo(this).showTimeLine(username);
	}
	
	@Get("/{username}")
	public void showTimeLine(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		List<Payload> payloads = pushevent.findPayloadsOf(username);
		
		payloads = commitFilesLoader.load(payloads, MAX_PAYLOADS);
		
		
		result.include("username", username);
		result.include("payloads", payloads);
	}
	
}
