package de.hsw.konsens.homer.client;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

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
		System.err.println(query);
		SearchHits r = service.getElasticsearch().client().prepareSearch().setIndices(index).setQuery(QueryBuilders.queryString(query)).setSize(500).execute().actionGet().getHits();
		List<String> ret = new  ArrayList<String>();
		for ( SearchHit sh : r) {
			ret.add(sh.getSourceAsString());
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
