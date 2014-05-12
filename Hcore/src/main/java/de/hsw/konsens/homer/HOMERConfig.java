package de.hsw.konsens.homer;

/**
 * Created by mielke on 01.04.2014.
 */
public class HOMERConfig {

    public static final String ELASTICSEARCH_HOST = "localhost";
    public static final int ELASTICSEARCH_PORT = 9300;
    public static final String ELASTICSEARCH_INDEX = "homer";
    public static final String ELASTICSEARCH_DOCUMENT = "document";

    public static final String URL_SPARQL_HOST = "http://localhost:3030/";
    public static final String URL_SPARQL_REPO = "HOMER";
    public static final String URL_SPARQL_ENDPOINT = URL_SPARQL_HOST + URL_SPARQL_REPO;

    public static final String URL_STANBOL_ENDPOINT = "http://localhost:8080/enhancer";
}
