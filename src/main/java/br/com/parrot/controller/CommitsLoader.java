package br.com.parrot.controller;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import br.com.caelum.vraptor.ioc.Component;
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.PushEvent;

@Component
public class CommitsLoader {

	public Set<PushEvent> loadFrom(Set<PushEvent> events, int max) {
		Set<PushEvent> subEvents = new TreeSet<PushEvent>();
		
		int i = 0;
		for (PushEvent event : events) {

			List<Commit> commits = event.getCommits();
			for (Commit commit : commits) {
				commit.load();
			}
			
			Collections.sort(commits);
			

			subEvents.add(event);
			if(++i >= max) {
				break;
			}
		}
		return subEvents;
	}

}
