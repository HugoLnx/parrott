package br.com.parrot.github;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.client.HttpResponseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import br.com.parrot.GetRequest;
import br.com.parrot.github.model.CommitFile;
import br.com.parrot.github.model.Line;
import br.com.parrot.github.model.StatusLine;

public class CommitFilesFinder {
	private final GetRequest get;
	private final FinderControl control;

	public CommitFilesFinder(GetRequest get) {
		this.get = get;
		this.control = new FinderControl();
	}
	
	public List<CommitFile> find(URI commitUri) {
		String commitJsonStr = "";
		try {
			commitJsonStr = get.responseBody(commitUri);
		} catch(HttpResponseException e) {
			return new ArrayList<CommitFile>();
		}
		
		
		JSONTokener jsonTokener = new JSONTokener(commitJsonStr);
		JSONObject filesJsonObject;
		List<CommitFile> files;
		try {
			filesJsonObject = new JSONObject(jsonTokener);
			JSONArray filesJson = filesJsonObject.getJSONArray("files");
			
			files = parseCommitFiles(filesJson);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao parsear JSON");
		}

		return files;
	}

	private List<CommitFile> parseCommitFiles(JSONArray filesJson) throws JSONException {
		List<CommitFile> files = new ArrayList<CommitFile>();
		
		for(int i = 0; i <= filesJson.length()-1; i++) {
			JSONObject fileJson = filesJson.getJSONObject(i);
			CommitFile file = parseCommitFile(fileJson);
			
			if(file != null) {
				files.add(file);
			}
		}
		return files;
	}

	private CommitFile parseCommitFile(JSONObject fileJson) throws JSONException {
		if (!fileJson.has("patch")) {
			return null;
		}
		String filename = fileJson.getString("filename");
		String patch = fileJson.getString("patch");
		
		List<Line> text = parsePath(patch);
		
		return new CommitFile(filename,text);
	}

	private List<Line> parsePath(String patch) {
		String [] resposta = patch.split(Pattern.quote("\n"));
		List<Line> text = new ArrayList<Line>();
		
		for ( String linha : resposta ){
			if( linha.startsWith("@") ){
				continue;
			}
			
			Line line = new Line();
			line.setContent(linha.substring(1));
			
			if ( linha.startsWith("+") ){
				line.setStatus(StatusLine.ADDED);
			}else if( linha.startsWith("-") ){
				line.setStatus(StatusLine.REMOVED);
			}else{
				line.setStatus(StatusLine.NOT_MODIFIED);
			}
			
			text.add(line);
		}
		return text;
	}
}
