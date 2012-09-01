package com.parrot.vraptor.controller;

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
public class TesteController {
	
	private final Result result; 
	private final PushEvents pushevent;
	
	public TesteController(Result result, PushEvents pushevents){
		super();
		this.pushevent = pushevents;
		this.result = result;
	}
	
	@Get
	@Path("/home/parrot")
	public void showSearchPage(){
		
	}
	
	@Get
	@Path("/home/parrot/search/apresentation")
	public List<Payload> showSearchPage(List<Payload> payloadList){
		return payloadList;
	}
	
	@Post
	@Path("/home/parrot/search")
	public void showSearchResults(String userName) throws ClientProtocolException, JSONException, IOException, URISyntaxException{
		result.redirectTo(this).showSearchPage(pushevent.getListOfPushEventsUrl(pushevent.getPublicEventsAsString(pushevent.buildPublicEventsUri(userName))));
	}
	
}
