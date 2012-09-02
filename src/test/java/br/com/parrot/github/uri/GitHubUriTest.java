package br.com.parrot.github.uri;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

public class GitHubUriTest {

	@Test
	public void getUriForAnUserFollowingJson() throws URISyntaxException {
		URI uri = new GitHubUri().following("ashton");
		assertEquals("http://localhost:8081/users/ashton/following", uri.toString());
	}
}
