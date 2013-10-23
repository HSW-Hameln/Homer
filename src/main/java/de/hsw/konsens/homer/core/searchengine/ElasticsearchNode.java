package de.hsw.konsens.homer.core.searchengine;

import org.elasticsearch.client.Client;

public interface ElasticsearchNode {
	public Client client();
}
