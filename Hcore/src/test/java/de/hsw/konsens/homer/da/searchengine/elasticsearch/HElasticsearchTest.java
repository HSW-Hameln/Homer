package de.hsw.konsens.homer.da.searchengine.elasticsearch;

import org.apache.jena.atlas.json.JsonObject;
import org.apache.jena.atlas.logging.Log;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by mielke on 17.04.2014.
 */
public class HElasticsearchTest {
    @Test(priority=1)
    public void connectionTest(){
        HElasticsearch elasticsearch = new HElasticsearch();
        Assert.assertTrue(elasticsearch.isConnected());
        elasticsearch.close();
    }

    @Test(priority=2)
    public void clearTest(){
        HElasticsearch elasticsearch = new HElasticsearch();
        elasticsearch.clear();
        elasticsearch.close();
    }

    @Test(priority=3)
    public void insertTest(){
        HElasticsearch elasticsearch = new HElasticsearch();
        JsonObject jo = new JsonObject();
        jo.put("name", "car");
        jo.put("value", "5");
        elasticsearch.insert(jo.toString());
        elasticsearch.close();
    }

    @Test(priority=4)
    public void findTest(){
        HElasticsearch elasticsearch = new HElasticsearch();
        SearchResponse r = elasticsearch.find("car");
        SearchHit[] h = r.getHits().getHits();
        Assert.assertEquals(h.length, 1);

        Log.info(this, h[0].getSourceAsString());

        elasticsearch.close();
    }
}
