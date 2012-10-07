package br.com.parrot.github.uri;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

public class GitHubUriTest {

	@Test
	public void getUriForAnUserFollowingJson() throws URISyntaxException {
		URI uri = new GitHubUri().following("ashton");
		assertEquals("https://api.github.com/users/ashton/following?page=1", uri.toString());
	}
	
	@Test
	public void getUriForAnUserFollowingJsonUsingPagination() throws URISyntaxException {
		URI uri = new GitHubUri().following("ashton", 4);
		assertEquals("https://api.github.com/users/ashton/following?page=4", uri.toString());
	}
	
	@Test
	public void getUriForAnUserPublicEventsJson() throws URISyntaxException {
		URI uri = new GitHubUri().publicEvents("ashton");
		assertEquals("https://api.github.com/users/ashton/events/public?page=1", uri.toString());
	}
	
	@Test
	public void getUriForAnUserPublicEventsJsonUsingPagination() throws URISyntaxException {
		URI uri = new GitHubUri().publicEvents("ashton", 2);
		assertEquals("https://api.github.com/users/ashton/events/public?page=2", uri.toString());
	}
}
