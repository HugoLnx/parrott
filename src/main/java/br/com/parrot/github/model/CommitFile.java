package br.com.parrot.github.model;

import java.io.Serializable;

public class CommitFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3689207809209581271L;
	
	private String fileName;
	private String patch;
	
	public CommitFile(){
		super();
	}
	
	public CommitFile(String fileName, String patch) {
		super();
		this.fileName = fileName;
		this.patch = patch;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPatch() {
		return patch;
	}
	public void setPatch(String patch) {
		this.patch = patch;
	}
	
	

}
