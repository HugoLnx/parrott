package br.com.parrot.controller;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.Payload;

@Component
public class CommitFilesLoader {

	public void loadAllIn(List<Payload> payloads) {
		for (Payload payload : payloads) {
			List<Commit> commits = payload.getCommits();
			for (Commit commit : commits) {
				commit.loadCommitFiles();
			}
		}
	}

}
