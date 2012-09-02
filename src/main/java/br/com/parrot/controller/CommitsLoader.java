package br.com.parrot.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import br.com.caelum.vraptor.ioc.Component;
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.Payload;

@Component
public class CommitsLoader {

	public Set<Payload> loadFrom(Set<Payload> payloads, int max) {
		Set<Payload> subPayloads = new TreeSet<Payload>();
		
		int i = 0;
		for (Payload payload : payloads) {

			List<Commit> commits = payload.getCommits();
			for (Commit commit : commits) {
				commit.load();
			}
			
			Collections.sort(commits);
			

			subPayloads.add(payload);
			if(++i >= max) {
				break;
			}
		}
		return subPayloads;
	}

}
