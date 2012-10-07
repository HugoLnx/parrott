package br.com.parrot.github.finder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import br.com.parrot.GetRequest;
import br.com.parrot.github.model.PushEvent;
import br.com.parrot.github.uri.GitHubUri;

public class MultipleUsersEventsFinder {

	private final GitHubUri gituri;

	public MultipleUsersEventsFinder(GitHubUri gituri) {
		this.gituri = gituri;
	}

	public Set<PushEvent> findEvents(List<String> users) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		Set<PushEvent> events = new TreeSet<PushEvent>();
		for (String user : users) {
			EventsFinder finder = new EventsFinder(gituri, new GetRequest());
			Set<PushEvent> userEvents = finder.findEventsOf(user);
			events.addAll(userEvents);
		}
		
		return events;
	}
}
