package br.com.parrot.github.finder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import br.com.parrot.github.uri.GitHubUri;

public class FollowingFinder {

	private final GitHubUri gituri;

	public FollowingFinder(GitHubUri gituri) {
		this.gituri = gituri;
	}

	public List<String> findFollowingFrom(String username) throws ClientProtocolException, IOException, URISyntaxException, JSONException {
		String eventsJsonStr = getResponseBody(gituri.following(username));
		
		JSONTokener followingTokener = new JSONTokener(eventsJsonStr);
		JSONArray followingJson = new JSONArray(followingTokener);
		
		return parseUsernames(followingJson);
	}
	
	private List<String> parseUsernames(JSONArray followingJson) throws JSONException {
		List<String> users = new ArrayList<String>();
		
		for (int i = 0; i < followingJson.length(); i++) {
			JSONObject userJson = followingJson.getJSONObject(i);
			users.add(userJson.getString("login"));
		}
		
		return users;
	}

	private String getResponseBody(URI uri) throws ClientProtocolException, IOException{
		return Request.Get(uri).execute().returnContent().asString();
	}
}
