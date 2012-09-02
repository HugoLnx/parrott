package br.com.parrot.github;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class PayloadsFinderControl {
	private int commitFilesLength;
	private int commitFilesLimit;
	
	public PayloadsFinderControl() {
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
