package br.com.parrot.github.uri;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

public class GitHubUri {
	private static final String PROTOCOL = "http"; // https
	private static final String EVENTS_URI = "/users/{username}/events/public";
	private static final String HOST = "localhost"; // api.github.com
	private static final int PORT = 8081; // 403

	public URI publicEvents(String user) throws URISyntaxException {
		URIBuilder builder = getUriBuilder();
		builder.setPath(getEventsPathFor(user));
		builder.setPort(PORT);
		return builder.build();
	}

	private String getEventsPathFor(String username) {
		return EVENTS_URI.replace("{username}", username);
	}

	private URIBuilder getUriBuilder() {
		URIBuilder builder = new URIBuilder();
		builder.setScheme(PROTOCOL).setHost(HOST);
		return builder;
	}
	
}
