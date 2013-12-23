package de.hsw.konsens.homer.core.searchengine.robot;

import java.io.File;
import java.io.IOException;

import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.client.Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Index {
	
	public class UnstructuredData {

		private String path = null;
		private String content = null;
		
		public UnstructuredData(String canonicalPath, String parseToString) {
			setPath(canonicalPath);
			setContent(parseToString);
		}
		
		@Override
		public String toString() {
			ObjectMapper mapper = new ObjectMapper();
			try {
				return mapper.writeValueAsString(this);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}

	Client c = null ;
	
	public Index(Client c) {
		this.c = c;
	}
	
	public void add(File file){
		if(file.isDirectory())
			addDir(file);
		else if(file.isFile())
		{
//			Tika tika = new Tika();
//			try {
//				c.prepareIndex("data", "content").setSource(new UnstructuredData(file.getCanonicalPath(),tika.parseToString(file)).toString()).get();
//				c.admin().indices().flush(new FlushRequest("data"));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (TikaException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

	private void addDir(File file) {
		File[] fl = file.listFiles();
		
		for (File f : fl) {
			add(f);
		}
	}
}
