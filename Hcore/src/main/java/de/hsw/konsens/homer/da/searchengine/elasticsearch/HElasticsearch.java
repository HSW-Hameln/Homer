package de.hsw.konsens.homer.da.searchengine.elasticsearch;

import de.hsw.konsens.homer.HOMERConfig;
import de.hsw.konsens.homer.da.HAbstractRemoteConnection;
import org.apache.jena.atlas.logging.Log;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoRequest;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.indices.IndexMissingException;
import org.elasticsearch.transport.RemoteTransportException;

import java.util.concurrent.ExecutionException;

/**
 * Created by mielke on 01.04.2014.
 * <p>
 * This class is a connection to an elasticsearch search engine.
 */
public class HElasticsearch extends HAbstractRemoteConnection {
    Client client = null;

    /**
     * The default constructor creates a new TransportClient to connect to an elastic search cluster.
     *
     * @see org.elasticsearch.client.transport.TransportClient
     */
    public HElasticsearch() {

        Settings settings = ImmutableSettings.settingsBuilder()
//                .put("cluster.name", "myClusterName")
                .put("client.transport.ignore_cluster_name", "true")
                .build();
        client = new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress(HOMERConfig.ELASTICSEARCH_HOST, HOMERConfig.ELASTICSEARCH_PORT));
    }

    /**
     * The find Method
     *
     * @param query As query language only the elasticsearch DSL is allowed.
     * @return Returns the SearchResponse of the request.
     * @see org.elasticsearch.action.search.SearchResponse
     */
    public SearchResponse find(String query) {
        try {
            return client.prepareSearch(HOMERConfig.ELASTICSEARCH_INDEX).setTypes(HOMERConfig.ELASTICSEARCH_DOCUMENT)
                    .addHighlightedField("content", 0, 0)
                    .setHighlighterPreTags(HOMERConfig.ELASTICSEARCH_HIGHLIGHT_PRE)
                    .setHighlighterPostTags(HOMERConfig.ELASTICSEARCH_HIGHLIGHT_POST)
                    .setHighlighterEncoder("html")
                    .setQuery(QueryBuilders.queryString(query)).setExplain(true).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        client.close();
    }

    /**
     * Deletes the indexes of the search engine.
     *
     * @return Always false. Not implemented.
     */
    @Override
    public boolean clear() {
        try {
            //DELETE IF EXISTS:
            try {
                client.admin().indices().delete(new DeleteIndexRequest(HOMERConfig.ELASTICSEARCH_INDEX)).get();
            } catch (ExecutionException e) {
                if (e.getCause() instanceof RemoteTransportException)
                    if (e.getCause().getCause() instanceof IndexMissingException)
                        Log.info(this, "Index " + HOMERConfig.ELASTICSEARCH_INDEX + " does not exist. Nothing to delete.");
                    else
                        e.printStackTrace();
            }
            //CREATE NEW INDEX:
            //client.admin().indices().create(new CreateIndexRequest(HOMERConfig.ELASTICSEARCH_INDEX)).get();
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Indexes data.
     *
     * @param document Document is a serialized version of a JSON object.
     * @return True if successful.
     */
    @Override
    public boolean insert(String... document) {
        try {
            for (String doc : document)
                client.prepareIndex(HOMERConfig.ELASTICSEARCH_INDEX, HOMERConfig.ELASTICSEARCH_DOCUMENT).setSource(doc).execute().get();
            client.admin().indices().flush(new FlushRequest(HOMERConfig.ELASTICSEARCH_INDEX)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This Method tests if the connection to an elasticsearch cluster can be established.
     *
     * @return True if test was successful.
     */
    @Override
    public boolean isConnected() {
        try {
            NodesInfoResponse res = client.admin().cluster().nodesInfo(new NodesInfoRequest()).get();

            if (res.getNodes().length > 0)
                return true;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
