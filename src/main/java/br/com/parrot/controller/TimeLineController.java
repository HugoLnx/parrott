package br.com.parrot.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.CommitFile;
import br.com.parrot.github.model.Payload;
import br.com.parrot.github.repository.PushEvents;
import br.com.parrot.github.uri.GitHubUri;

@Resource
@Path("/timeline")
public class TimeLineController {
	
	private final Result result; 
	private final PushEvents pushevent;
	private final GitHubUri gitUri;
	
	public TimeLineController(Result result, PushEvents pushevents, GitHubUri gitUri){
		this.pushevent = pushevents;
		this.result = result;
		this.gitUri = gitUri;
	}

	@Get("/")
	public void redirectToTimeline(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException {
		result.redirectTo(this).showTimeLine(username);
	}
	
	@Get("/{username}")
	public void showTimeLine(String username) throws ClientProtocolException, JSONException, IOException, URISyntaxException{
		List<Payload> payloads = pushevent.getListOfPushEventsUrl(pushevent.getResponseBody(gitUri.publicEvents(username)));
		for (Payload payload : payloads) {
			for (Commit commit : payload.getCommits()) {
				URI buildStrCommit = URI.create(commit.getUrl());
				String commitsStr = pushevent.getResponseBody(buildStrCommit);
				List<CommitFile> jsonFiles = pushevent.getFilesOfCommitUrl(commitsStr);
				commit.setCommitFiles(jsonFiles);
			}
		}
		result.include("username", username);
		result.include("payloads",payloads);
	}
	
}
