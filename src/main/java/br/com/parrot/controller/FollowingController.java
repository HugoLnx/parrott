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
import br.com.caelum.vraptor.view.Results;
import br.com.parrot.github.FollowingFinder;
import br.com.parrot.github.MultipleUsersPayloadsFinder;
import br.com.parrot.github.model.Payload;
import br.com.parrot.github.uri.GitHubUri;

@Resource
@Path("/following")
public class FollowingController {
	
	private GitHubUri gituri;
	private final Result result;
	
	public FollowingController(GitHubUri gituri, Result result) {
		this.gituri = gituri;
		this.result = result;
	}
	
	@Get("/")
	public void redirectToTimeline(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		result.redirectTo(this).following(username);
	}
	

	@Get("/{username}")
	public void following(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		FollowingFinder followingFinder = new FollowingFinder(gituri);
		List<String> users = followingFinder.findFollowingFrom(username);
		
		MultipleUsersPayloadsFinder payloadsFinder = new MultipleUsersPayloadsFinder(gituri);
		List<Payload> payloads = payloadsFinder.findPayloads(users);
		
		result.include("payloads", payloads);
		result.use(Results.page()).of(TimeLineController.class).showTimeLine(null);
	}
}
