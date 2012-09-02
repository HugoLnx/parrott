package br.com.parrot.github.repository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import br.com.caelum.vraptor.ioc.Component;
import br.com.parrot.github.PayloadsFinderControl;
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.CommitFile;
import br.com.parrot.github.model.Line;
import br.com.parrot.github.model.Payload;
import br.com.parrot.github.model.StatusLine;
import br.com.parrot.github.uri.GitHubUri;


@Component
public class PayloadsFinder {
	private final GitHubUri gituri;
	private final PayloadsFinderControl control;

	public PayloadsFinder(GitHubUri gituri, PayloadsFinderControl control) {
		this.gituri = gituri;
		this.control = control;
	}
	
	public List<Payload> findPayloadsOf(String username, int limitOfCommitFiles)
			throws JSONException, ClientProtocolException, IOException, URISyntaxException, ParseException {
		control.setCommitFilesLimit(limitOfCommitFiles);
		String eventsJsonStr = getResponseBody(gituri.publicEvents(username));
		
		JSONTokener eventsTokener = new JSONTokener(eventsJsonStr);
		JSONArray eventsJson = new JSONArray(eventsTokener);
		
		List<Payload> payloadList = parseEventsJson(eventsJson);
		return payloadList;
	}

	private List<Payload> parseEventsJson(JSONArray eventsJson) throws JSONException, ParseException,
			ClientProtocolException, IOException {

		List<Payload> payloads = new ArrayList<Payload>();
		for (int i = 0; i <= eventsJson.length() - 1; i++) {
			JSONObject eventJson = eventsJson.getJSONObject(i);
			Payload payload = parseEvent(eventJson);
			
			if(payload != null) {
				payloads.add(payload);
				if(control.isParsedEnough()) break;
			}
		}
		return payloads;
	}

	private Payload parseEvent(JSONObject eventJson) throws JSONException,
			ParseException, ClientProtocolException, IOException {
		if (!eventJson.getString("type").equalsIgnoreCase("PushEvent")) {
			return null;
		}
		JSONObject payloadJson = eventJson.getJSONObject("payload");
		Payload payload = parsePayload(eventJson, payloadJson);

		return payload;
	}

	private Payload parsePayload(JSONObject eventJson, JSONObject payloadJson) throws ParseException, JSONException,
			ClientProtocolException, IOException {
		if(!payloadJson.has("commits")) {
			return null;
		}

		Payload payload = new Payload(payloadJson.getString("ref"),
				eventJson.getString("type"),
				eventJson.getString("created_at"),
				eventJson.getString("id"),
				eventJson.getJSONObject("repo"));

		JSONArray commitsJson = payloadJson.getJSONArray("commits");

		List<Commit> commitList = parseCommits(commitsJson);
		
		payload.setCommits(commitList);
		return payload;
	}

	private List<Commit> parseCommits(JSONArray commitsJson) throws JSONException,
			ClientProtocolException, IOException {
		List<Commit> commits = new ArrayList<Commit>();
		for (int j = 0; j <= commitsJson.length() - 1; j++) {
			JSONObject commitJson = commitsJson.getJSONObject(j);
			Commit commit = parseCommit(commitJson);
			
			if(commit != null) {
				commits.add(commit);
				if(control.isParsedEnough()) break;
			}
		}
		return commits;
	}

	private Commit parseCommit(JSONObject commitJson) throws JSONException,
			ClientProtocolException, IOException {
		Commit commit = new Commit(commitJson.getJSONObject(
				"author").getString("name"),
				commitJson.getString("message"),
				commitJson.getString("url"));
		
		URI commitUri = URI.create(commit.getUrl());
		List<CommitFile> files = findCommitFiles(commitUri);
		
		if(files.isEmpty()) {
			return null;
		} else {
			commit.setCommitFiles(files);
			return commit;
		}
		
	}

	private List<CommitFile> findCommitFiles(URI commitUri)
			throws ClientProtocolException, IOException, JSONException {
		String commitJsonStr = "";
		try {
			commitJsonStr = getResponseBody(commitUri);
		} catch(HttpResponseException e) {
			return new ArrayList<CommitFile>();
		}
		
		
		JSONTokener jsonTokener = new JSONTokener(commitJsonStr);
		JSONObject filesJsonObject = new JSONObject(jsonTokener);
		JSONArray filesJson = filesJsonObject.getJSONArray("files");

		List<CommitFile> files = parseCommitFiles(filesJson);

		return files;
	}

	private List<CommitFile> parseCommitFiles(JSONArray filesJson) throws JSONException {
		List<CommitFile> files = new ArrayList<CommitFile>();
		
		for(int i = 0; i <= filesJson.length()-1; i++) {
			JSONObject fileJson = filesJson.getJSONObject(i);
			CommitFile file = parseCommitFile(fileJson);
			
			if(file != null) {
				files.add(file);
				control.addedOneCommitFile();
				if(control.isParsedEnough()) 	break;
			}
		}
		return files;
	}

	private CommitFile parseCommitFile(JSONObject fileJson) throws JSONException {
		if (!fileJson.has("patch")) {
			return null;
		}
		String filename = fileJson.getString("filename");
		String patch = fileJson.getString("patch");
		
		List<Line> text = parsePath(patch);
		
		return new CommitFile(filename,text);
	}

	private List<Line> parsePath(String patch) {
		String [] resposta = patch.split(Pattern.quote("\n"));
		List<Line> text = new ArrayList<Line>();
		
		for ( String linha : resposta ){
			if( linha.startsWith("@") ){
				continue;
			}
			
			Line line = new Line();
			line.setContent(linha.substring(1));
			
			if ( linha.startsWith("+") ){
				line.setStatus(StatusLine.ADDED);
			}else if( linha.startsWith("-") ){
				line.setStatus(StatusLine.REMOVED);
			}else{
				line.setStatus(StatusLine.NOT_MODIFIED);
			}
			
			text.add(line);
		}
		return text;
	}
	

	private String getResponseBody(URI uri) throws ClientProtocolException, IOException{
		return Request.Get(uri).execute().returnContent().asString();
	}
}
