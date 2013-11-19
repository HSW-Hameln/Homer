package de.hsw.konsens.homer;

import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.suggest.Suggest.Suggestion;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry.Option;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.annotations.Test;

import de.hsw.konsens.homer.core.rdf_store.SesameRepositoryConnection;

public class ElasticSearchTest {
	private FileSystemXmlApplicationContext spring = new FileSystemXmlApplicationContext("beans.xml");

	public void beforeClass() {
			spring = new FileSystemXmlApplicationContext("beans.xml");
			
		}
	
	@Test(groups = "local")
	public void localTest() {
		Node n = (Node)spring.getBean("elasticsearch_local");
		SesameRepositoryConnection rdfcon = (SesameRepositoryConnection) spring.getBean("sesame_repository_connection");
		Client c = n.client();
		
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
			
			c.admin().indices().flush(new FlushRequest("sail")).actionGet();
			
			SearchResponse response = c.prepareSearch().setIndices("sail").setQuery(QueryBuilders.queryString("langer")).setSize(50).execute().actionGet();

			for (SearchHit h : response.getHits()) {
				System.out.println(h.getSource());
			}

		n.close();
	}

	@Test(groups = "remote")
	public void remoteTest() {

	}
}
