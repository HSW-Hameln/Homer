package de.hsw.konsens.homer;

import de.hsw.konsens.homer.da.HConnection;
import de.hsw.konsens.homer.da.searchengine.elasticsearch.HElasticsearch;
import de.hsw.konsens.homer.da.triplestore.fuseki.HFuseki;

/**
 * Created by mielke on 01.04.2014.
 */
public class HOMERAdmin {
    /**
     * Static helper method. The prepareEnvironment() method initializes the data sources. ATTENTION: ALL DATA WILL BE LOST.
     */
    static void prepareEnvironment(){
        HConnection elsasticsearch = new HElasticsearch();
        HConnection fuseki = new HFuseki();
//        HConnection stanbol = new HStanbol();

        if (elsasticsearch.isConnected())
        {
            elsasticsearch.clear();
            elsasticsearch.insert();
        }

        if (fuseki.isConnected())
        {
            fuseki.clear();
            fuseki.insert();
        }
    }
}
