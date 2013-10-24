package de.hsw.konsens.homer.service;

import de.hsw.konsens.homer.core.parser.HomerParser;
import de.hsw.konsens.homer.core.searchengine.ElasticsearchNode;

public class HomerService {
	
	private ElasticsearchNode searchengine;
	private HomerParser parser;
	public ElasticsearchNode getSearchengine() {
		return searchengine;
	}
	public void setSearchengine(ElasticsearchNode searchengine) {
		this.searchengine = searchengine;
	}
	public HomerParser getParser() {
		return parser;
	}
	public void setParser(HomerParser parser) {
		this.parser = parser;
	}

	
	
//administrative operations
	
	//maintain search engine.
		//Add documents to search index.
		//remove documents from search index.
		
		//create index for semantic repository.
	
	//maintain semantic repositories.
		//add ad hoc ontology .
		//remove parts of the ontology network.
		//check repositories for consistency
		
	
//user operations
	
	//autocomplete
		//get subjects
		//get predicates
		//get preview 
	
	//search
		//return document URLs and metadata
	
}
