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
import br.com.parrot.github.model.Payload;
import br.com.parrot.github.uri.GitHubUri;

public class MultipleUsersPayloadsFinder {

	private final GitHubUri gituri;

	public MultipleUsersPayloadsFinder(GitHubUri gituri) {
		this.gituri = gituri;
	}

	public Set<Payload> findPayloads(List<String> users) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		Set<Payload> payloads = new TreeSet<Payload>();
		for (String user : users) {
			EventsFinder finder = new EventsFinder(gituri, new GetRequest());
			Set<Payload> userPayloads = finder.findEventsOf(user);
			payloads.addAll(userPayloads);
		}
		
		return payloads;
	}
}
