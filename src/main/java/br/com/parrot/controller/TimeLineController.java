package br.com.parrot.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;


import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.parrot.github.model.Payload;
import br.com.parrot.github.repository.PushEvents;

@Resource
@SuppressWarnings("unused")
public class TimeLineController {
	
	private final Result result; 
	private final PushEvents pushevent;
	
	public TimeLineController(Result result, PushEvents pushevents){
		super();
		this.pushevent = pushevents;
		this.result = result;
	}
	
	@Get
	@Path("/parrot")
	public List<Payload> showTimeLine(List<Payload> payloadList){
		return payloadList;
	}
	
	@Post
	@Path("/parrot/search.user")
	public void showSearchResults(String userName) throws ClientProtocolException, JSONException, IOException, URISyntaxException{
		result.forwardTo(this).showTimeLine(pushevent.getListOfPushEventsUrl(pushevent.getPublicEventsAsString(pushevent.buildPublicEventsUri(userName))));
	}
	
}
