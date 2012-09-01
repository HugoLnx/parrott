package br.com.parrot.github.repository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import br.com.caelum.vraptor.ioc.Component;
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.CommitFile;
import br.com.parrot.github.model.Line;
import br.com.parrot.github.model.Payload;
import br.com.parrot.github.model.StatusLine;
import br.com.parrot.github.uri.GitHubUri;


@Component
public class PushEvents {
	
	public static void main(String[] args) throws URISyntaxException,
			ClientProtocolException, IOException, JSONException {
		GitHubUri gitUri = new GitHubUri();
		PushEvents events = new PushEvents();
		String user = "hugolnx";
		URI uri = gitUri.publicEvents(user);
		String eventsStr = events.getResponseBody(uri);
		ArrayList<Payload> payloads = (ArrayList<Payload>) events
				.getListOfPushEventsUrl(eventsStr);
		for (Payload payload : payloads) {
			ArrayList<Commit> commits = (ArrayList<Commit>) payload
					.getCommits();
			for (Commit commit : commits) {
				URI buildStrCommit = URI.create(commit.getUrl());
				String commitsStr = events.getResponseBody(buildStrCommit);
				List<CommitFile> jsonFiles = events.getFilesOfCommitUrl(commitsStr);
				commit.setCommitFiles(jsonFiles);
				
				for (CommitFile file : jsonFiles) {
					System.out.println(file.getFileName());
					List<Line> lines = file.getLines();
					for (Line line : lines) {
						System.out.println(line.getContent());
					}
				}
			}
			
			
		}
	}
	
	public String getResponseBody(URI uri) throws ClientProtocolException, IOException{
		return Request.Get(uri).execute().returnContent().asString();
	}
	
	public List<Payload> getListOfPushEventsUrl(String jsonAsString)
			throws JSONException {
		List<Payload> payloadList = new ArrayList<Payload>();
		JSONTokener jasonTokener = new JSONTokener(jsonAsString);
		JSONArray gitHubJson = new JSONArray(jasonTokener);
		for (int i = 0; i <= gitHubJson.length() - 1; i++) {
			List<Commit> commitList = new ArrayList<Commit>();
			JSONObject jsonObject = gitHubJson.getJSONObject(i);
			if (jsonObject.getString("type").equalsIgnoreCase("PushEvent")) {
				JSONObject pushevent = jsonObject.getJSONObject("payload");
				Payload payload = new Payload(pushevent.getString("ref"),
						pushevent.getString("before"),
						pushevent.getString("push_id"),
						pushevent.getString("head"),
						jsonObject.getString("type"),
						jsonObject.getString("created_at"),
						jsonObject.getString("id"),
						jsonObject.getJSONObject("repo"));
				JSONArray commits = pushevent.getJSONArray("commits");
				for (int j = 0; j <= commits.length() - 1; j++) {
					JSONObject commitAsJson = commits.getJSONObject(j);
					Commit commit = new Commit(commitAsJson.getJSONObject(
							"author").getString("name"),
							commitAsJson.getString("message"),
							commitAsJson.getString("url"));
					commitList.add(commit);
				}
				payload.setCommits(commitList);
				payloadList.add(payload);
			}
		}
		return payloadList;
	}

	public List<CommitFile> getFilesOfCommitUrl(String jsonAsString) throws JSONException {
		List<CommitFile> commitFileList = new ArrayList<CommitFile>();
		
		JSONTokener jasonTokener = new JSONTokener(jsonAsString);
		JSONObject files = new JSONObject(jasonTokener);
		JSONArray arrayFiles = files.getJSONArray("files");
		
		for(int i = 0; i <= arrayFiles.length()-1; i++) {
			if (arrayFiles.getJSONObject(i).has("patch")) {
				String fileName = arrayFiles.getJSONObject(i).getString("filename");
				String patch = arrayFiles.getJSONObject(i).getString("patch");
				
				String [] resposta = patch.split(Pattern.quote("\n"));
				List<Line> text = new ArrayList<Line>();
				
				for ( String a : resposta ){
					if( a.startsWith("@") ){
						continue;
					}
					
					Line line = new Line();
					line.setContent(a.substring(1));
					
					if ( a.startsWith("+") ){
						line.setStatus(StatusLine.ADDED);
					}else if( a.startsWith("-") ){
						line.setStatus(StatusLine.REMOVED);
					}else{
						line.setStatus(StatusLine.NOT_MODIFIED);
					}
					
					text.add(line);
					
					CommitFile commitFile = new CommitFile(fileName,text);
					commitFileList.add(commitFile);
				}
			}
		}
		return commitFileList;
	}
}
