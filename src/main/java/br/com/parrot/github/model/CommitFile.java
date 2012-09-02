package br.com.parrot.github.model;

import java.util.List;

public class CommitFile {

	private String fileName;
	private List<Line> lines;
	private String blobUri;
	private String rawUri;
	
	public CommitFile(String fileName, List<Line> lines, String blobUri, String rawUri) {
		this.fileName = fileName;
		this.lines = lines;
		this.blobUri = blobUri;
		this.rawUri = rawUri;
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

	public String getBlobUri() {
		return blobUri;
	}

	public String getRawUri() {
		return rawUri;
	}
}
