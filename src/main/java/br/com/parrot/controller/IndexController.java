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
import br.com.parrot.exceptions.HttpNotFoundException;
import br.com.parrot.github.finder.CommitsLoader;
import br.com.parrot.github.finder.EventsFinder;
import br.com.parrot.github.finder.MultipleUsersEventsFinder;
import br.com.parrot.github.model.PushEvent;

@Resource
public class IndexController {
	private static final int MAX_EVENTS = 5;
	private static final List<String> DEFAULT_USERS = Arrays.asList(
		"guilhermesilveira", "tenderlove", "unclebob",
		"akitaonrails", "josevalim", "fnando", "emerleite"
	);
	
	private final Result result;
	private final CommitsLoader commitsLoader;
	private final EventsFinder finder;
	
	public IndexController(Result result, CommitsLoader commitFilesLoader, EventsFinder finder) {
		this.result = result;
		this.commitsLoader = commitFilesLoader;
		this.finder = finder;
	}
	
	@Get("/")
	public void index() {
	}
	
	@Get("/index/events")
	public void events() throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException, HttpNotFoundException {
		String username = randomDeveloper();
		int page = randomPage();
		Set<PushEvent> events = finder.findEventsOf(username, page);
		
		events = commitsLoader.loadFrom(events, MAX_EVENTS);
		
		result.include("events", events);
	}

	private int randomPage() {
		return new Random().nextInt(9) + 1;
	}

	private String randomDeveloper() {
		int i = new Random().nextInt(DEFAULT_USERS.size());
		return DEFAULT_USERS.get(i);
	}
}
