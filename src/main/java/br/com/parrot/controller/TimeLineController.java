package br.com.parrot.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.parrot.github.model.Payload;
import br.com.parrot.github.repository.PushEvents;

@Resource
@Path("/timeline")
public class TimeLineController {
	
	private static final int FILES_LIMIT = 10;
	private final Result result; 
	private final PushEvents pushevent;
	
	public TimeLineController(Result result, PushEvents pushevents){
		this.pushevent = pushevents;
		this.result = result;
	}

	@Get("/")
	public void redirectToTimeline(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException {
		result.redirectTo(this).showTimeLine(username);
	}
	
	@Get("/{username}")
	public void showTimeLine(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException {
		List<Payload> payloads = pushevent.getListOfPushEventsUrl(username, FILES_LIMIT);
		
		result.include("username", username);
		result.include("payloads",payloads);
	}
	
}
