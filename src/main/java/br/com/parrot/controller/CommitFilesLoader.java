package br.com.parrot.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.Payload;

@Component
public class CommitFilesLoader {

	public List<Payload> load(List<Payload> payloads, int max) {
		List<Payload> subPayloads = new ArrayList<Payload>();
		
		int i = 0;
		for (Payload payload : payloads) {
			List<Commit> commits = payload.getCommits();
			for (Commit commit : commits) {
				commit.loadCommitFiles();
			}
			subPayloads.add(payload);
			if(++i >= max) {
				break;
			}
		}
		return subPayloads;
	}

}
