package de.hsw.konsens.homer.service;

import de.hsw.konsens.homer.core.parser.HomerParser;
import de.hsw.konsens.homer.core.rdf_store.SesameRepositoryConnection;
import de.hsw.konsens.homer.core.searchengine.ElasticsearchNode;
public class HomerService {
	
	private ElasticsearchNode elasticsearch;
//	private PluginManager pluginManager;
	private SesameRepositoryConnection openRDFSesame;
	private HomerParser parser;

	public HomerParser getParser() {
		return parser;
	}
	public void setParser(HomerParser parser) {
		this.parser = parser;
	}

	public ElasticsearchNode getElasticsearch() {
		return elasticsearch;
	}
	public void setElasticsearch(ElasticsearchNode elasticsearch) {
		this.elasticsearch = elasticsearch;
	}
//	public PluginManager getPluginManager() {
//		return pluginManager;
//	}
//	public void setPluginManager(PluginManager pluginManager) {
//		this.pluginManager = pluginManager;
//	}
	public SesameRepositoryConnection getOpenRDFSesame() {
		return openRDFSesame;
	}
	public void setOpenRDFSesame(SesameRepositoryConnection openRDFSesame) {
		this.openRDFSesame = openRDFSesame;
	}
	
}
