package de.hsw.konsens.homer.core.parser;

import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import de.hsw.konsens.homer.core.triplestore.SesameRepositoryConnection;

public class ELCommands {
	private SesameRepositoryConnection connection = null;
	
	public String samePredicate(String subject, String predicate) throws RepositoryException, MalformedQueryException, QueryEvaluationException
	{
		return sparql("SELECT ?l WHERE {"+subject+" "+predicate+" ?o. ?s ?p ?o. ?s rdfs:label ?l}");
	}
	
	public String sparql(String query) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		return sparql(query, "", "");
	}
	
	public String sparql(String query,String prefix) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
			return sparql(query, prefix, "");
	}
	
	public String sparql(String query,String prefix, String postfix) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		return convertToSearchString(connection.tupleQuery(query));
	}

	public SesameRepositoryConnection getConnection() {
		return connection;
	}

	public void setConnection(SesameRepositoryConnection connection) {
		this.connection = connection;
	}
	
	private String convertToSearchString(String result)
	{
		String s = result.trim().replace("\" \"", "\" AND \"").replace("\n", " OR ");
		
		return "("+s+")";
	}
}
