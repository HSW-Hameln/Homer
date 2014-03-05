package de.hsw.konsens.homer.core.searchengine.robot;

import java.io.File;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class IndexTest {
	private ClassPathXmlApplicationContext spring = new ClassPathXmlApplicationContext("beans.xml");

	@Test
	public void add() {
		Node n = (Node) spring.getBean("elasticsearch_local");
		new Index(n.client()).add(new File("content"));
		try {
			n.client().admin().indices().flush(new FlushRequest("data")).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void status() {
		Node n = (Node) spring.getBean("elasticsearch_local");

		SearchResponse response = n.client().prepareSearch().setIndices("data").setQuery(QueryBuilders.queryString("2200")).setSize(50).execute().actionGet();

		for (SearchHit h : response.getHits()) {
			System.out.println(h.getSource());
		}

		n.client().close();
		n.close();
	}

	
}
