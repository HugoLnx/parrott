package br.com.parrot.github.finder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import br.com.caelum.vraptor.ioc.Component;
import br.com.parrot.exceptions.HttpNotFoundException;
import br.com.parrot.github.model.PushEvent;
import br.com.parrot.github.uri.GitHubUri;

@Component
public class MultipleUsersEventsFinder {

	private final EventsFinder finder;

	public MultipleUsersEventsFinder(GitHubUri gituri, EventsFinder finder) {
		this.finder = finder;
	}

	public Set<PushEvent> findEvents(List<String> users) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		return findEvents(users, 1);
	}
	
	public Set<PushEvent> findEvents(List<String> users, int page) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		Set<PushEvent> events = new TreeSet<PushEvent>();
		for (String user : users) {
			Set<PushEvent> userEvents;
			try {
				userEvents = finder.findEventsOf(user, page);
				events.addAll(userEvents);
			} catch (HttpNotFoundException e) {	}
		}
		
		return events;
	}
}
