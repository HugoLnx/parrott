package br.com.parrot.github;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import br.com.parrot.github.model.Payload;
import br.com.parrot.github.repository.PayloadsFinder;
import br.com.parrot.github.uri.GitHubUri;

public class MultipleUsersPayloadsFinder {

	private final GitHubUri gituri;

	public MultipleUsersPayloadsFinder(GitHubUri gituri) {
		this.gituri = gituri;
	}

	public List<Payload> findPayloads(List<String> users) throws ClientProtocolException, JSONException, IOException, URISyntaxException, ParseException {
		List<Payload> payloads = new ArrayList<Payload>();
		for (String user : users) {
			PayloadsFinder finder = new PayloadsFinder(gituri, new PayloadsFinderControl());
			System.out.println(user);
			List<Payload> userPayloads = finder.findPayloadsOf(user, 5);
			payloads.addAll(userPayloads);
		}
		
		return payloads;
	}
}
