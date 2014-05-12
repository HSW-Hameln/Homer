package de.hsw.konsens.homer;

import de.hsw.konsens.homer.da.searchengine.elasticsearch.HElasticsearch;
import de.hsw.konsens.homer.expressionlanguage.HOMERExpressionLanguage;
import de.hsw.konsens.homer.expressionlanguage.HOMERExpressionLanguageConfig;
import org.apache.jena.atlas.json.JsonArray;
import org.elasticsearch.search.SearchHit;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mielke on 08.04.2014.
 */
public class HOMER {
    public void HOMER(){

    }

    public Map<String,String> search(String query){

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();

        ctx.register(HOMERExpressionLanguageConfig.class,HOMERExpressionLanguage.class);
        ctx.refresh();

        HOMERExpressionLanguage parser = ctx.getBean(HOMERExpressionLanguage.class);
        String equery = parser.parse(query);

        HElasticsearch elasticsearch = new HElasticsearch();
        SearchHit[] hits = elasticsearch.find(equery).getHits().getHits();
        JsonArray hitlist = new JsonArray();
        for (int i = 0 ; i<hits.length ; i++)
            hitlist.add(hits[i].getSourceAsString());
        Map returnMap = new HashMap<String, String>();
        returnMap.put("QUERY",query);
        returnMap.put("EXTENDED",equery);
        returnMap.put("HITS", hitlist);
        return returnMap;
    }
}
