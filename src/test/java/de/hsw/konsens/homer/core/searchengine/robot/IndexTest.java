package de.hsw.konsens.homer.core.searchengine.robot;

import java.io.File;

import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.node.Node;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.annotations.Test;

public class IndexTest {
	private FileSystemXmlApplicationContext spring = new FileSystemXmlApplicationContext(
			"beans.xml");

	@Test
	public void add() {
		Node n = (Node) spring.getBean("elasticsearch_local");
		new Index(n.client()).add(new File("content"));
		n.client().admin().indices().flush(new FlushRequest("data"));
		n.client().close();
		n.close();
	}
}
