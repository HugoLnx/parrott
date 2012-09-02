package br.com.parrot.github.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Payload implements Comparable<Payload> {
	private String ref;
	private String type;
	private List<Commit> commits;
	private Calendar createdAt;
	private String id;
	private String login;
	
	
	public Payload(String ref,	String type, String createdAt, String id, String login) throws ParseException {
		this.ref = ref;
		this.type = type;
		this.createdAt = parseData(createdAt);
		this.id = id;
		this.login = login;
	}

	private Calendar parseData(String timestamp) throws ParseException {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormatter= new SimpleDateFormat("HH:mm:ss");
		int tIndex = timestamp.indexOf('T');
		String dateStr = timestamp.substring(0, tIndex - 1);
		String timeStr = timestamp.substring(tIndex + 1, timestamp.length() - 1);
		
		Calendar date = Calendar.getInstance();
		date.setTime(dateFormatter.parse(dateStr));
		Calendar time = Calendar.getInstance();
		time.setTime(timeFormatter.parse(timeStr));
		date.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
		date.set(Calendar.SECOND, time.get(Calendar.SECOND));
		return date;
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
	public Calendar getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public int compareTo(Payload o) {
		return o.getCreatedAt().compareTo(this.getCreatedAt());
	}
	
}
