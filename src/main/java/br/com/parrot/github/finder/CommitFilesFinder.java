package br.com.parrot.github.finder;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import br.com.parrot.GetRequest;
import br.com.parrot.github.model.Commit;
import br.com.parrot.github.model.CommitFile;
import br.com.parrot.github.model.Line;
import br.com.parrot.github.model.StatusLine;

public class CommitFilesFinder {
	private final GetRequest get;

	public CommitFilesFinder(GetRequest get) {
		this.get = get;
	}
	
	private Calendar parseData(String timestamp) throws ParseException {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		int tIndex = timestamp.indexOf('T');
		String dateStr = timestamp.substring(0, tIndex);
		
		Calendar date = Calendar.getInstance();
		date.setTime(dateFormatter.parse(dateStr));

		return date;
	}
	
	public Calendar getCommitDate(JSONObject commitObject) {
		Calendar dataCommit= null;		
		try {
			String dataStr = commitObject.getJSONObject("commit").getJSONObject("author").getString("date");
			
			dataCommit = parseData(dataStr);
		} catch (JSONException je) {
			je.printStackTrace();
			throw new RuntimeException("Erro ao parsear JSON"); 
		} catch (ParseException pe) {
			pe.printStackTrace();
			throw new RuntimeException("Erro ao parsear Data Commit");
		}
		return dataCommit;
	}
	
	public List<CommitFile> getCommitFiles(JSONObject commitObject) {
		List<CommitFile> files;
		try {
			JSONArray filesJson = commitObject.getJSONArray("files");
			
			files = parseCommitFiles(filesJson);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao parsear JSON");
		}

		return files;
	}
	
	public void load(Commit commit) {
		URI commitUri = URI.create(commit.getUrl());
		List<CommitFile> files;
		String commitJsonStr = "";
		try {
			commitJsonStr = get.responseBody(commitUri);
		} catch (ClientProtocolException e) {
			commit.setCommitFiles(new ArrayList<CommitFile>());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("get Response Body deu erro no CommitFilesFinder");
		}
		
		
		JSONTokener commitTokener = new JSONTokener(commitJsonStr);
		JSONObject commitJson;
		Calendar date;
		try {
			commitJson = new JSONObject(commitTokener);
			
			JSONArray filesJson = commitJson.getJSONArray("files");
			files = parseCommitFiles(filesJson);
			
			String timestamp = commitJson.getJSONObject("commit")
					.getJSONObject("author")
					.getString("date");
			date = parseData(timestamp);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao parsear JSON");
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao parsear JSON");
		}
		
		commit.setCommitFiles(files);
		commit.setDate(date);

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
		String blobUri = fileJson.getString("blob_url");
		String rawUri = fileJson.getString("raw_url");
		
		List<Line> text = parsePath(patch);
		
		return new CommitFile(filename,text, blobUri, rawUri);
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
