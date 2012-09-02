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
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.CommitFile;
import br.com.parrot.github.model.Line;
import br.com.parrot.github.model.Payload;
import br.com.parrot.github.model.StatusLine;
import br.com.parrot.github.uri.GitHubUri;


@Component
public class PushEvents {
	private final GitHubUri gituri;


	public PushEvents(GitHubUri gituri) {
		this.gituri = gituri;
	}
	
	public List<Payload> getListOfPushEventsUrl(String username, int limitOfFiles)
			throws JSONException, ClientProtocolException, IOException, URISyntaxException, ParseException {
		String jsonAsString = getResponseBody(gituri.publicEvents(username));
		
		List<Payload> payloadList = new ArrayList<Payload>();
		JSONTokener jasonTokener = new JSONTokener(jsonAsString);
		JSONArray gitHubJson = new JSONArray(jasonTokener);
		int filesSize = 0;
		for (int i = 0; i <= gitHubJson.length() - 1; i++) {
			List<Commit> commitList = new ArrayList<Commit>();
			JSONObject jsonObject = gitHubJson.getJSONObject(i);
			if (jsonObject.getString("type").equalsIgnoreCase("PushEvent")) {
				JSONObject pushevent = jsonObject.getJSONObject("payload");
				Payload payload = new Payload(pushevent.getString("ref"),
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
					
					URI buildStrCommit = URI.create(commit.getUrl());
					String commitsStr = "";
					try {
						commitsStr = getResponseBody(buildStrCommit);
					} catch(HttpResponseException e) {
						continue;
					}
					List<CommitFile> jsonFiles = getFilesOfCommitUrl(commitsStr);

					if(!jsonFiles.isEmpty()) {
						commit.setCommitFiles(jsonFiles);
					
						commitList.add(commit);
						filesSize += commit.getCommitFiles().size();
						if(filesSize >= limitOfFiles) {
							payload.setCommits(commitList);
							payloadList.add(payload);
							
							return payloadList;
						}
					}
				}
				payload.setCommits(commitList);
				payloadList.add(payload);
				
				if(filesSize >= limitOfFiles) return payloadList;
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
				CommitFile commitFile = new CommitFile(fileName,text);
				commitFileList.add(commitFile);
			}
		}
		return commitFileList;
	}
	

	private String getResponseBody(URI uri) throws ClientProtocolException, IOException{
		return Request.Get(uri).execute().returnContent().asString();
	}
}
