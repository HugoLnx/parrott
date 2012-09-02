package br.com.parrot.github.model;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

public class Payload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3230632225736522387L;

	private String ref;
	private String type;
	private List<Commit> commits;
	private String createdAt;
	private String id;
	private JSONObject repo;
	
	public Payload(){
		super();
	}
	
	public Payload(String ref,	String type, String createdAt, String id, JSONObject repo) {
		this.ref = ref;
		this.type = type;
		this.createdAt = createdAt;
		this.id = id;
		this.repo = repo;
	}

	public String getRef() {
		return ref;
	}
	
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Commit> getCommits() {
		return commits;
	}
	public void setCommits(List<Commit> commits) {
		this.commits = commits;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public JSONObject getRepo() {
		return repo;
	}
	public void setRepo(JSONObject repo) {
		this.repo = repo;
	}
	
	
}
