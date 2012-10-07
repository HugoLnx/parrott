package br.com.parrot.github.finder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import br.com.caelum.vraptor.ioc.Component;
import br.com.parrot.GetRequest;
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.PushEvent;
import br.com.parrot.github.uri.GitHubUri;


@Component
public class EventsFinder {
	private final GitHubUri gituri;
	private final GetRequest get;

	public EventsFinder(GitHubUri gituri, GetRequest get) {
		this.gituri = gituri;
		this.get = get;
	}
	
	public Set<PushEvent> findEventsOf(String username)
			throws JSONException, ClientProtocolException, IOException, URISyntaxException, ParseException {
		String eventsJsonStr = get.responseBody(gituri.publicEvents(username));
		
		JSONTokener eventsTokener = new JSONTokener(eventsJsonStr);
		JSONArray eventsJson = new JSONArray(eventsTokener);
		
		Set<PushEvent> events = parseEventsJson(eventsJson);
		return events;
	}

	private Set<PushEvent> parseEventsJson(JSONArray eventsJson) throws JSONException, ParseException,
			ClientProtocolException, IOException {

		Set<PushEvent> events = new TreeSet<PushEvent>();
		for (int i = 0; i <= eventsJson.length() - 1; i++) {
			JSONObject eventJson = eventsJson.getJSONObject(i);
			PushEvent event = parseEvent(eventJson);
			
			if(event != null) {
				events.add(event);
			}
		}
		return events;
	}

	private PushEvent parseEvent(JSONObject eventJson) throws JSONException,
			ParseException, ClientProtocolException, IOException {
		if (!eventJson.getString("type").equalsIgnoreCase("PushEvent")) {
			return null;
		}
		JSONObject payloadJson = eventJson.getJSONObject("payload");
		PushEvent event = parseEvent(eventJson, payloadJson);

		return event;
	}

	private PushEvent parseEvent(JSONObject eventJson, JSONObject payloadJson) throws ParseException, JSONException,
			ClientProtocolException, IOException {
		if(!payloadJson.has("commits")) {
			return null;
		}

		JSONObject actorJson = eventJson.getJSONObject("actor");
		PushEvent event = new PushEvent(payloadJson.getString("ref"),
				eventJson.getString("type"),
				eventJson.getString("created_at"),
				eventJson.getString("id"),
				actorJson.getString("login"),
				actorJson.getString("avatar_url"));
		
		JSONArray commitsJson = payloadJson.getJSONArray("commits");

		List<Commit> commitList = parseCommits(commitsJson,eventJson);
		
		event.setCommits(commitList);
		return event;
	}

	private List<Commit> parseCommits(JSONArray commitsJson,JSONObject eventJson) throws JSONException,
			ClientProtocolException, IOException {

		List<Commit> commits = new ArrayList<Commit>();
		for (int j = 0; j <= commitsJson.length() - 1; j++) {
			JSONObject commitJson = commitsJson.getJSONObject(j);
			Commit commit = parseCommit(commitJson, eventJson);
			
			if(commit != null) {
				commits.add(commit);
			}
		}
		return commits;
	}

	private Commit parseCommit(JSONObject commitJson, JSONObject eventJson) throws JSONException, ClientProtocolException, IOException {
		Commit commit = new Commit(commitJson.getJSONObject(
				"author").getString("name"),
				commitJson.getString("message"),
				commitJson.getString("url"));
		
		return commit;
			
		
		
	}
}
