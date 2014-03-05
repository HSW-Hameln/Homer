package de.hsw.konsens.homer.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.hsw.konsens.homer.service.HomerService;

public class HomerClientLocal implements HomerClient {
	private HomerService service;

	public HomerClientLocal(HomerService service) {
		setService(service);
	}

	public String extend(String expressionString) {
		return service.getParser().parse(expressionString);
	}
	
	public  List<String> search(String index, String q) {
		String query = service.getParser().parse(q);
//		System.err.println(query);
		SearchHits r = service.getElasticsearch().client().prepareSearch()
				.setIndices(index)
				.setQuery(QueryBuilders.queryString(query))
				.setSize(500)
				.setHighlighterPreTags("<b>")
				.setHighlighterPostTags("</b>")
				.addHighlightedField("content", 0, 0)
					.execute().actionGet().getHits();
		
		List<String> ret = new  ArrayList<String>();
		ObjectMapper mapper = new ObjectMapper();
		for ( SearchHit sh : r) {
			Map<String, Object> t = sh.getSource();
//			Map<String, Object> h = new HashMap<>();
//			for(String s : sh.getHighlightFields().keySet())
//				h.put(s,sh.getHighlightFields().get(s).getFragments()[0].string());
			try {
//				t.put("highlight",mapper.writeValueAsString(h));
				t.put("query", query);
//				System.out.println(mapper.writeValueAsString(t));
				ret.add(mapper.writeValueAsString(t));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}

	public HomerService getService() {
		return service;
	}

	public void setService(HomerService service) {
		this.service = service;
	}


}
