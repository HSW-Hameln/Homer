package de.hsw.konsens.homer.core.rdf_store;

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
}
