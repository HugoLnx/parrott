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
import br.com.caelum.vraptor.view.Results;
import br.com.parrot.github.finder.FollowingFinder;
import br.com.parrot.github.finder.MultipleUsersEventsFinder;
import br.com.parrot.github.model.PushEvent;
import br.com.parrot.github.uri.GitHubUri;

@Resource
@Path("/following")
public class FollowingController {
	
	private static final int MAX_PAYLOADS = 5;
	private GitHubUri gituri;
	private final Result result;
	private final CommitsLoader commitsLoader;
	private final MultipleUsersEventsFinder eventsFinder;
	
	public FollowingController(GitHubUri gituri, Result result, CommitsLoader commitFilesLoader, MultipleUsersEventsFinder eventsFinder) {
		this.gituri = gituri;
		this.result = result;
		this.commitsLoader = commitFilesLoader;
		this.eventsFinder = eventsFinder;
	}
	
	@Get("/")
	public void redirectToTimeline(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		result.redirectTo(this).following(username);
	}
	

	@Get("/{username}")
	public void following(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		result.forwardTo(this).following(username, 1);
	}
	
	@Get("/{username}/{page}")
	public void following(String username, int page) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		FollowingFinder followingFinder = new FollowingFinder(gituri);
		List<String> users = followingFinder.findFollowingFrom(username);
		
		Set<PushEvent> events = eventsFinder.findEvents(users, page);
		
		events = commitsLoader.loadFrom(events, MAX_PAYLOADS);
		
		result.include("events", events);
		result.include("username", username);
		result.include("page", page);
	}
}
