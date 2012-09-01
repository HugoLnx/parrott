package br.com.parrot.github.model;

import java.io.Serializable;

public enum StatusLine implements Serializable{
	
	
	ADDED("+"), 
	REMOVED("-"), 
	NOT_MODIFIED(" ");
	
	private String sinal;
	
	StatusLine(String sinal) {
		this.sinal  = sinal;
	}

	public String getSinal() {
		return sinal;
	}
}
