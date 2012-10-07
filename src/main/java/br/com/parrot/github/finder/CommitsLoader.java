package br.com.parrot.github.finder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import br.com.caelum.vraptor.ioc.Component;
import br.com.parrot.exceptions.HttpNotFoundException;
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.PushEvent;

@Component
public class CommitsLoader {

	public Set<PushEvent> loadFrom(Set<PushEvent> events, int max) {
		Set<PushEvent> subEvents = new TreeSet<PushEvent>();
		
		int i = 0;
		for (PushEvent event : events) {

			List<Commit> commits = new ArrayList<Commit>();
			for (Commit commit : event.getCommits()) {
				try {
					commit.load();
					commits.add(commit);
					commit.setValid(true);
				} catch (HttpNotFoundException e){
				}
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
