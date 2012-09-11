package br.com.parrot.github.uri;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

public class GitHubUriTest {

	@Test
	public void getUriForAnUserFollowingJson() throws URISyntaxException {
		URI uri = new GitHubUri().following("ashton");
		assertEquals("https://api.github.com/users/ashton/following", uri.toString());
	}
}
