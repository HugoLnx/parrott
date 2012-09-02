package br.com.parrot.github.model;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import br.com.parrot.GetRequest;
import br.com.parrot.github.CommitFilesFinder;
import br.com.parrot.github.FinderControl;

public class Commit {

	private String author;
	private String sha;
	private String distinct;
	private String message;
	private String url;
	private List<CommitFile> commitFiles;
	
	public Commit(String author, String message, String url) {
		this.author = author;
		this.message = message;
		this.url = url;
	}
	
	public void loadCommitFiles() {
		CommitFilesFinder finder = new CommitFilesFinder(new GetRequest());
		commitFiles = finder.find(URI.create(getUrl()));
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSha() {
		return sha;
	}
	public void setSha(String sha) {
		this.sha = sha;
	}
	public String getDistinct() {
		return distinct;
	}
	public void setDistinct(String distinct) {
		this.distinct = distinct;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public void setCommitFiles(List<CommitFile> commitFiles) {
		this.commitFiles = commitFiles;	
	}

	public List<CommitFile> getCommitFiles() {
		if(commitFiles != null) {
			loadCommitFiles();
		}
		return commitFiles;
	}
	
}
		