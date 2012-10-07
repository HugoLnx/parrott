package br.com.parrot.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.parrot.github.finder.CommitsLoader;
import br.com.parrot.github.finder.MultipleUsersEventsFinder;
import br.com.parrot.github.model.PushEvent;

@Resource
public class IndexController {
	private static final int MAX_EVENTS = 5;
	private static final List<String> DEFAULT_USERS = Arrays.asList("guilhermesilveira", "tenderlove", "unclebob", "akitaonrails");
	private final Result result;
	private final CommitsLoader commitsLoader;
	private final MultipleUsersEventsFinder eventsFinder;
	
	public IndexController(Result result, CommitsLoader commitFilesLoader, MultipleUsersEventsFinder eventsFinder) {
		this.result = result;
		this.commitsLoader = commitFilesLoader;
		this.eventsFinder = eventsFinder;
	}
	
	@Get("/")
	public void index() {
	}
	
	@Get("/index/events")
	public void events() throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		List<String> users = DEFAULT_USERS;
		int page = new Random().nextInt(10);
		Set<PushEvent> events = eventsFinder.findEvents(users, page);
		
		events = commitsLoader.loadFrom(events, MAX_EVENTS);
		
		result.include("events", events);
	}
}
