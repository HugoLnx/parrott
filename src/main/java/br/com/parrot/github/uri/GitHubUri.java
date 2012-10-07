package br.com.parrot.github.uri;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.utils.URIBuilder;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class GitHubUri {
	private static final String PROTOCOL = "https";
	private static final String EVENTS_PATH = "/users/{username}/events/public";
	private static final String FOLLOWING_PATH = "/users/{username}/following";
	private static final String HOST = "api.github.com";

	public URI publicEvents(String user) throws URISyntaxException {
		return publicEvents(user, 1);
	}
	
	public URI publicEvents(String user, int page) throws URISyntaxException {
		String path = EVENTS_PATH.replace("{username}", user);
		return uriWithPath(path, page);
	}
	
	public URI following(String user) throws URISyntaxException {
		return following(user, 1);
	}
	
	public URI following(String user, int page) throws URISyntaxException {
		String path = FOLLOWING_PATH
				.replace("{username}", user);
		return uriWithPath(path, page);
	}

	private URI uriWithPath(String path, int page) throws URISyntaxException {
		return getUriBuilder()
				.setPath(path)
				.setParameter("page", String.valueOf(page))
				.build();
	}

	private URIBuilder getUriBuilder() {
		URIBuilder builder = new URIBuilder();
		builder.setScheme(PROTOCOL)
			.setHost(HOST);
		return builder;
	}

	
}
