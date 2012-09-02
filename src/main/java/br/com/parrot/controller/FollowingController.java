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
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.Payload;
import br.com.parrot.github.uri.GitHubUri;

@Resource
@Path("/following")
public class FollowingController {
	
	private static final int MAX_PAYLOADS = 5;
	private GitHubUri gituri;
	private final Result result;
	private final CommitFilesLoader commitFilesLoader;
	
	public FollowingController(GitHubUri gituri, Result result, CommitFilesLoader commitFilesLoader) {
		this.gituri = gituri;
		this.result = result;
		this.commitFilesLoader = commitFilesLoader;
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
		List<Payload> payloads = payloadsFinder.findPayloads(users).subList(0, MAX_PAYLOADS);
		commitFilesLoader.loadAllIn(payloads);
		
		result.include("payloads", payloads);
		result.use(Results.page()).of(TimeLineController.class).showTimeLine(null);
	}
}
