package br.com.parrot.github.model;

import java.util.Calendar;
import java.util.List;

import br.com.parrot.GetRequest;
import br.com.parrot.github.finder.CommitFilesFinder;

public class Commit implements Comparable<Commit> {

	private String author;
	private String sha;
	private String distinct;
	private String message;
	private String url;
	private Calendar date;
	private List<CommitFile> commitFiles;
	
	public Commit(String author, String message, String url) {
		this.author = author;
		this.message = message;
		this.url = url;
	}
	
	public void load() {
		CommitFilesFinder finder = new CommitFilesFinder(new GetRequest());
		finder.load(this);
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

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public void setCommitFiles(List<CommitFile> commitFiles) {
		this.commitFiles = commitFiles;	
	}

	public List<CommitFile> getCommitFiles() {
		if(commitFiles != null) {
			load();
		}
		return commitFiles;
	}

	@Override
	public int compareTo(Commit o) {
		return o.getDate().compareTo(this.getDate());
	}
	
}
		