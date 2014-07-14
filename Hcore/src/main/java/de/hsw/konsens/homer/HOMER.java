package de.hsw.konsens.homer;

import de.hsw.konsens.homer.da.searchengine.elasticsearch.HElasticsearch;
import de.hsw.konsens.homer.expressionlanguage.HOMERExpressionLanguage;
import de.hsw.konsens.homer.expressionlanguage.HOMERExpressionLanguageConfig;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mielke on 08.04.2014.
 */
public class HOMER {
    public void HOMER(){

    }

    public Map<String,String> search(String query){

        // Spring
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();
        ctx.register(HOMERExpressionLanguageConfig.class,HOMERExpressionLanguage.class);
        ctx.refresh();

        // SpEL Expand Query
        HOMERExpressionLanguage parser = ctx.getBean(HOMERExpressionLanguage.class);
        String equery = parser.parse(query);

        // Execute Search
        HElasticsearch elasticsearch = new HElasticsearch();
        SearchHit[] hits = elasticsearch.find(equery).getHits().getHits();

        //  Post processing
        List hitlist = new ArrayList<Map>();
        for (int i = 0; i < hits.length; i++) {
            Map<String, Map> hit = new HashMap<>();
            hit.put("source", hits[i].getSource());
            Map<String, Object> highlighted = new HashMap<>();
            for (String key : hits[i].getHighlightFields().keySet()) {
                StringBuffer highlightedText = new StringBuffer();
                for (Text text : hits[i].getHighlightFields().get(key).getFragments())
                    highlightedText.append(text.toString());
                highlighted.put(key, highlightedText.toString());
                highlighted.put("terms", filterTerms(highlightedText.toString()));
            }

            hit.put("highlighted", highlighted);
            hitlist.add(hit);
        }

        Map returnMap = new HashMap<String, String>();
        returnMap.put("QUERY",query);
        returnMap.put("EXTENDED",equery);
        returnMap.put("HITS", hitlist);
        return returnMap;
    }

    private List<String> filterTerms(String s) {
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("(" + HOMERConfig.ELASTICSEARCH_HIGHLIGHT_PRE + ".*?" + HOMERConfig.ELASTICSEARCH_HIGHLIGHT_POST + "\\s*)+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            //for( int i = 0 ; i < matcher.groupCount() ; i++)
            list.add(matcher.group(0).replaceAll(HOMERConfig.ELASTICSEARCH_HIGHLIGHT_PRE, "").replaceAll(HOMERConfig.ELASTICSEARCH_HIGHLIGHT_POST, "").trim());
        }
        return list;
    }
}
