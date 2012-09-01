package br.com.parrot.github.model;

import java.io.Serializable;
import java.util.List;

public class CommitFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3689207809209581271L;
	
	private String fileName;
	private List<Line> lines;
	
	public CommitFile(){
		super();
	}
	
	public CommitFile(String fileName, List<Line> lines) {
		super();
		this.fileName = fileName;
		this.setLines(lines);
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Line> getLines() {
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}
}
