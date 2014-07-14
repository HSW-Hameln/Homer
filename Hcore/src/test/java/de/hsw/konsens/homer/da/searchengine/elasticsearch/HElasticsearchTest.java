package de.hsw.konsens.homer.da.searchengine.elasticsearch;

import de.hsw.konsens.homer.HOMERUtilities;
import org.apache.jena.atlas.logging.Log;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by mielke on 17.04.2014.
 */
public class HElasticsearchTest {
    @Test(priority = 1)
    public void connectionTest() {
        HElasticsearch elasticsearch = new HElasticsearch();
        Assert.assertTrue(elasticsearch.isConnected());
        elasticsearch.close();
    }

    @Test(priority = 2)
    public void clearTest() {
        HElasticsearch elasticsearch = new HElasticsearch();
        elasticsearch.clear();
        elasticsearch.close();
    }

    @Test(priority = 3)
    public void insertTest() {
        try {
            HOMERUtilities.index(new File("\\\\hsw-verwa\\user$\\mielke"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 4)
    public void findTest() {
        HElasticsearch elasticsearch = new HElasticsearch();
        SearchResponse r = elasticsearch.find("hallo");
        SearchHit[] h = r.getHits().getHits();
        Assert.assertTrue(h.length > 0);

        Log.info(this, h[0].getSourceAsString());

        elasticsearch.close();
    }
}
