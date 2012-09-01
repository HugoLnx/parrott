package br.com.parrot.github.model;

import java.io.Serializable;
import java.util.List;

public class Commit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7693389980006477643L;

	private String author;
	private String sha;
	private String distinct;
	private String message;
	private String url;
	private List<Line> lines;
	
	public Commit() { }

	public Commit(String author, String message, String url) {
		this.author = author;
		this.message = message;
		this.url = url;
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
	
	public List<Line> getLines() {
		return lines;
	}
	
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}
	
}
