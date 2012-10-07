package br.com.parrot.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.parrot.github.finder.EventsFinder;
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.PushEvent;

@Resource
@Path("/timeline")
public class TimeLineController {
	
	private static final int MAX_EVENTS = 5;
	private final Result result; 
	private final EventsFinder finder;
	private final CommitsLoader commitsLoader;
	
	public TimeLineController(Result result, EventsFinder pushevents, CommitsLoader commitFilesLoader){
		this.finder = pushevents;
		this.result = result;
		this.commitsLoader = commitFilesLoader;
	}

	@Get("/")
	public void redirectToTimeline(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		result.redirectTo(this).showTimeLine(username);
	}
	
	@Get("/{username}")
	public void showTimeLine(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		Set<PushEvent> events = finder.findEventsOf(username);
		
		events = commitsLoader.loadFrom(events, MAX_EVENTS);
		
		
		result.include("username", username);
		result.include("events", events);
	}
	
}
