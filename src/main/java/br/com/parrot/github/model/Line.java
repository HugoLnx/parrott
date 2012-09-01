package br.com.parrot.github.model;

public class Line {
	
	private StatusLine status; 
	private String content;
	
	public StatusLine getStatus() {
		return status;
	}
	public void setStatus(StatusLine status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
