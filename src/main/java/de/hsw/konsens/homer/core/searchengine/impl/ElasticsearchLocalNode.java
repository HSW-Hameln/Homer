package de.hsw.konsens.homer.core.searchengine.impl;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import de.hsw.konsens.homer.core.searchengine.ElasticsearchNode;

public class ElasticsearchLocalNode implements ElasticsearchNode{

	private Node node;

	public ElasticsearchLocalNode() {

		final Settings settings = ImmutableSettings.settingsBuilder()
				// disable REST-Interface
				.put("http.enabled", "false")
				// no discovery
				.put("multicast.enabled", "false").build();
		this.node = NodeBuilder.nodeBuilder().settings(settings).local(true).node();
	}

	public Client client() {
		return node.client();
	}
	
}
