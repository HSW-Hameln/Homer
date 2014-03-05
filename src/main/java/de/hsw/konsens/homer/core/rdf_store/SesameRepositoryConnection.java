package de.hsw.konsens.homer.core.rdf_store;

import java.io.IOException;
import java.util.ArrayList;

import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.openrdf.model.Value;
import org.openrdf.query.Binding;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class SesameRepositoryConnection {
	private Repository repository = null;
	private RepositoryConnection connection = null;
	public Repository getRepository() {
		return repository;
	}
	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	
	public String tupleQuery(String query) throws RepositoryException, MalformedQueryException, QueryEvaluationException
	{
		if(!repository.isInitialized())
			repository.initialize();
		if(connection == null || !connection.isOpen())
			connection = repository.getConnection();
		TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult tupleQueryResult = tupleQuery.evaluate();
		
		String returnValue = "";
		while(tupleQueryResult.hasNext()) {
			for(Binding binding : tupleQueryResult.next()) {
				returnValue += binding.getValue()+" ";
			}
			returnValue = returnValue.trim() + '\n';
		}
		
		return returnValue;
	}
	
	public void index(Client c) throws RepositoryException, MalformedQueryException, QueryEvaluationException{
		String query = "SELECT DISTINCT ?s WHERE{?s ?p ?o} ";
		
		AdminClient admin = c.admin();
		ActionFuture<IndicesExistsResponse> r = admin.indices().exists(new IndicesExistsRequest("sail"));
		if(r.actionGet().isExists())
			admin.indices().delete(new DeleteIndexRequest("sail")).actionGet();
		admin.indices().create(new CreateIndexRequest("sail")).actionGet();
				
		
		if(!repository.isInitialized())
			repository.initialize();
		if(connection == null || !connection.isOpen())
			connection = repository.getConnection();

		TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult tupleQueryResult = tupleQuery.evaluate();

		TupleQuery tq;
		TupleQueryResult tqr;
		String uri;
		
		while(tupleQueryResult.hasNext()) {
			uri = tupleQueryResult.next().getValue("s").stringValue();
			query = "SELECT ?p WHERE{<"+uri+"> ?p ?o} ";
			
			tq = connection.prepareTupleQuery(QueryLanguage.SPARQL, query);
			tqr = tq.evaluate();
			
		
			ArrayList<String> index = new ArrayList<>();
			
			while(tqr.hasNext())
			{
				index.add(trim(tqr.next().getValue("p")));				
			}

			try {
				c.prepareIndex("sail", "subject").setSource(XContentFactory.jsonBuilder()
						.startObject()
							.field("URI",uri)
							.array("ATTRIBUTE", index.toArray())
						.endObject()).get();
			} catch (ElasticSearchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		c.admin().indices().flush(new FlushRequest("sail"));

	}
	private String trim(Value value) {

		System.out.println(value);

		if(value.toString().charAt(0) == '\"')
			return value.toString().substring(1,value.toString().length()-1);
		return value.toString();
	}
	public void shutDown() {
		try {
			connection.close();
			repository.shutDown();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
