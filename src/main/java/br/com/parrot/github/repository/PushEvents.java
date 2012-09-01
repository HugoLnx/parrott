package br.com.parrot.github.repository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import br.com.caelum.vraptor.ioc.Component;
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.Payload;


@Component
public class PushEvents {

	public static final String ENDOFPUBLICEVENTSURI = "/events/public";
	/**
	 * @param args
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException, JSONException {
		// TODO Auto-generated method stub
		URIBuilder builder = new URIBuilder();
		PushEvents pe = new PushEvents();
		String user = "hugolnx";
		URI uri = pe.buildPublicEventsUri(builder, user);
		String eventsStr = pe.getPublicEventsAsString(uri);
		ArrayList<Payload> payloads = (ArrayList<Payload>) pe.getListOfPushEventsUrl(eventsStr);
		for(Payload payload : payloads){
			ArrayList<Commit> commits = (ArrayList<Commit>) payload.getCommits();
			for(Commit commit: commits){
				System.out.println(commit.getUrl());
			}
		}
	}

	/**
	 * 
	 * @param builder
	 * @param user
	 * @return uri
	 * @throws URISyntaxException
	 */
	public URI buildPublicEventsUri(URIBuilder builder,String user) throws URISyntaxException{
		builder.setScheme("https").setHost("api.github.com/users")
		.setPath("/" + user + ENDOFPUBLICEVENTSURI);
		URI uri = builder.build();
		return uri;
	}
	
	/**
	 * 
	 * @param uri
	 * @return String
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String getPublicEventsAsString(URI uri) throws ClientProtocolException, IOException{
		return Request.Get(uri).execute().returnContent().asString();
	}
	
	/**
	 * 
	 * @param jsonAsString
	 * @return List<String>
	 * @throws JSONException
	 */
	public List<Payload> getListOfPushEventsUrl(String jsonAsString) throws JSONException{
		List<Payload> payloadList = new ArrayList<Payload>();
		JSONTokener jasonTokener = new JSONTokener(jsonAsString);
		JSONArray gitHubJson = new JSONArray(jasonTokener);
		for(int i = 0; i <= gitHubJson.length()-1; i++){
			List<Commit> commitList = new ArrayList<Commit>();
			JSONObject jsonObject = gitHubJson.getJSONObject(i);
			if(jsonObject.getString("type").equalsIgnoreCase("PushEvent")){
				JSONObject pushevent = jsonObject.getJSONObject("payload");
				Payload payload = new Payload(pushevent.getString("ref"), pushevent.getString("before"), pushevent.getString("push_id"),
						pushevent.getString("head"), jsonObject.getString("type"), jsonObject.getString("created_at"),
						jsonObject.getString("id"), jsonObject.getJSONObject("repo"));
				JSONArray commits =  pushevent.getJSONArray("commits");
				for(int j = 0; j <= commits.length()-1; j++){
					JSONObject commitAsJson = commits.getJSONObject(j);
					Commit commit = new Commit(commitAsJson.getJSONObject("author").getString("name"), commitAsJson.getString("message"), 
							commitAsJson.getString("url"));
					commitList.add(commit);
				}
				System.out.println(commitList.size() + "tamanho da lista de commits de uma pushevent");
				payload.setCommits(commitList);
				payloadList.add(payload);
			}
		}
		System.out.println(payloadList.size() + "numero de push events");
		return payloadList;
	}
}
