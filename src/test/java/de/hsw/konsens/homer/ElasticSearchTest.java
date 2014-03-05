package de.hsw.konsens.homer;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.Test;

import de.hsw.konsens.homer.core.rdf_store.SesameRepositoryConnection;


public class ElasticSearchTest {
	private ApplicationContext spring = Homer.getSpringContext();

	public void beforeClass() {
			
		}
	
	@Test(groups = "local")
	public void localTest() {
		Node n = (Node)spring.getBean("elasticsearch_local");
		Client c = n.client();

		c.admin().indices().create(new CreateIndexRequest("data"));
		
		SesameRepositoryConnection rdfcon = (SesameRepositoryConnection) spring.getBean("sesame_repository_connection");
			try {
				rdfcon.index(c);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedQueryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (QueryEvaluationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			rdfcon.shutDown();
						
			SearchResponse response = c.prepareSearch().setIndices("sail").setQuery(QueryBuilders.queryString("langer")).setSize(50).execute().actionGet();

			for (SearchHit h : response.getHits()) {
				System.out.println(h.getSource());
			}

			n.client().close();
		n.stop();
		n.close();
	}

	@Test(groups = "remote")
	public void remoteTest() {

	}
}
