HOMER
=====

Search framework that uses Ontologies

Homer is a framework that is build on top of a search engine and a triplestore. In this implementation the search engine is elasticsearch and the triplestore is Apache Fuseki. The Framework serves a RESTfull interface that extends elasticsearch's Lucene DSL with the possibility of embedding SPARQL queries. This approach enables the user to generate complex queries from a semantic structure. 

Installation
------------

As first step HOMER needs a running [elasicsearch](http://www.elasticsearch.org/) instance and a running [fuseki](http://jena.apache.org/download/index.cgi) instance.

To start the search engine you just have to execute the program.
> C:\smielke\elasticsearch-1.1.1\bin\elasticsearch.bat

For running the tripplestore we need some options. In this case enabling SPARQL Update set the storage location and the service path.
> C:\smielke\jena-fuseki-1.0.1\fuseki-server.bat --update --loc Homer\ /HOMER


### Build from sources ###
To build this repository you just need to run the [gradle](http://www.gradle.org/) build management tool.

Usage
-----
As default you find the service at localhost on port 8080 under the path /homer/search. It expects an URL encoded POST request with a field called "request" and a value that is a valid query.

Examples
--------

### Import an Ontology ###
The HOMERUtilities Class contains some static methods that help you to get data into the framework. 
To acces these Methods without writing a program you can use the HOMER CLI. For example the command 

> \> HOMER utils import /path/to/rdf_file

imports an RDF document into the tripplestore.

### Indexing Files ###
You can use the HOMERUtilities class for indexing folders too.

### Requests ###
Elasticsearch serves a large range of methods to request data. The elasticsearch DSL distinguishes [queries](http://www.elasticsearch.org/guide/en/elasticsearch/reference/current/query-dsl-queries.html) and [filters](http://www.elasticsearch.org/guide/en/elasticsearch/reference/current/query-dsl-filters.html). Additionaly a search request to the service endpoint can contain expresion language terms. The expression Language will be executed by the [SpEL](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/expressions.html) parser, before it is sent to the search engine.

The answer of the service contains the query itself, the query after it is modified and the list of hits.

<pre>
{
    QUERY: "content:pdf OR ${latex}"
    EXTENDED: "content:pdf OR latex"
    QUERY: "content:pdf OR ${latex}"
}
</pre>


HITS is a JSON array which contains an object with two more objects. One is called "source" and the other one is called "highlighted". The source object contains two fields. Path which contains the path of the found document and content which contains its content. The highlighted object has an array of terms that was found in the document which is called "terms" and another content field witch contains the same text as the content field of the source object but the found terms are wrapped by a hilight tag.

<pre>
HITS: [10]
    0:  {
        highlighted: {
            terms: [1]
                0:  "pdf"
            content: "*.<hilight>pdf</hilight> *.aux *.toc "
        }
        source: {
            path: "\\hsw-verwa\user$\mielke\latex\Homer\.gitignore"
            content: "*.pdf *.aux *.toc "
        }
    }
    ...
}
</pre>