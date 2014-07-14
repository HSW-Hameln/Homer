package de.hsw.konsens.homer;

import de.hsw.konsens.homer.da.searchengine.elasticsearch.HElasticsearch;
import org.apache.jena.atlas.json.JsonObject;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import java.io.File;
import java.io.IOException;

/**
 * Created by mielke on 11.06.2014.
 */
public class HOMERUtilities {
    private static HElasticsearch elasticsearch = new HElasticsearch();

    public static void importOntology(File ontology) {

    }

    public static void importOntology(String ontology) {

    }

    public static void index(File file) throws IOException {
        indexRecursive(file);
        elasticsearch.close();
    }

    private static void indexRecursive(File file) {
        if (file.isFile())
            indexFile(file);
        else
            for (File f : file.listFiles())
                indexRecursive(f);
    }

    private static void indexFile(File file) {
        JsonObject jo = new JsonObject();
        try {
            jo.put("path", file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jo.put("content", new Tika().parseToString(file));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
        elasticsearch.insert(jo.toString());
    }


}
