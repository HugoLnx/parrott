package br.com.parrot.github;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class PayloadsParserControl {
	private int commitFilesLength;
	private int commitFilesLimit;
	
	public PayloadsParserControl() {
		this.commitFilesLength = 0;
		this.commitFilesLimit = 0;
	}
	
	public boolean isParsedEnough() {
		return commitFilesLimit != 0 && commitFilesLength > commitFilesLimit;
	}

	public void setCommitFilesLimit(int commitFilesLimit) {
		this.commitFilesLimit = commitFilesLimit;
	}

	public void addedOneCommitFile() {
		commitFilesLength++;
	}
	
}
