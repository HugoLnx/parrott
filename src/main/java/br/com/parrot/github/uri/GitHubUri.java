package br.com.parrot.github.uri;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class GitHubUri {
	private static final String PROTOCOL = "http"; // https
	private static final String EVENTS_PATH = "/users/{username}/events/public";
	private static final String FOLLOWING_PATH = "/users/{username}/following";
	private static final String HOST = "localhost"; // api.github.com
	private static final int PORT = 8081; // 403

	public URI publicEvents(String user) throws URISyntaxException {
		String path = EVENTS_PATH.replace("{username}", user);
		return uriWithPath(path);
	}
	
	public URI following(String user) throws URISyntaxException {
		String path = FOLLOWING_PATH.replace("{username}", user);
		return uriWithPath(path);
	}

	private URI uriWithPath(String path) throws URISyntaxException {
		return getUriBuilder()
				.setPath(path)
				.build();
	}

	private URIBuilder getUriBuilder() {
		URIBuilder builder = new URIBuilder();
		builder.setScheme(PROTOCOL)
			.setHost(HOST)
			.setPort(PORT);
		return builder;
	}

	
}
