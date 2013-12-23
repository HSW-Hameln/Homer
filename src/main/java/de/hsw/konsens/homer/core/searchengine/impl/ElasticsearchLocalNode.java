package de.hsw.konsens.homer.core.searchengine.impl;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import de.hsw.konsens.homer.core.searchengine.ElasticsearchNode;

public class ElasticsearchLocalNode implements ElasticsearchNode{

	private Node node =  NodeBuilder.nodeBuilder().settings(ImmutableSettings.settingsBuilder().put("http.enabled", "true")
			.put("multicast.enabled", "false").build()).local(true).node();

	public ElasticsearchLocalNode() {
//
//		final Settings settings = ImmutableSettings.settingsBuilder()
//				// disable REST-Interface
//				.put("http.enabled", "false")
//				// no discovery
//				.put("multicast.enabled", "false").build();
//		this.node = NodeBuilder.nodeBuilder().settings(settings).local(true).node();
	}

	public Client client() {
		return node.client();
	}
	
	public void close(){
		node.close();
	}

	@Override
	public Settings settings() {
		// TODO Auto-generated method stub
		return node.settings();
	}

	@Override
	public Node start() {
		// TODO Auto-generated method stub
		return node.start();
	}

	@Override
	public Node stop() {
		// TODO Auto-generated method stub
		return node.stop();
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return node.isClosed();
	}
	
}
