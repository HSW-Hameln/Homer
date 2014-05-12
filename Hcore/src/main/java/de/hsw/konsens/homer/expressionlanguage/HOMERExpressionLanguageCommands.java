package de.hsw.konsens.homer.expressionlanguage;

import de.hsw.konsens.homer.da.triplestore.fuseki.HFuseki;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mielke on 08.04.2014.
 */
public class HOMERExpressionLanguageCommands {

    public String sparql(String query) {
        HFuseki f = new HFuseki();
        ResultSet rs = f.query(query);
        String ret = defaultSearchStringBuiler(rs);
        f.close();
        return ret;
    }

    private String defaultSearchStringBuiler(ResultSet rs) {
        String search = "";

        try {
            while( rs.next() ) {
                search += search.length()>0 ? " OR ":"";
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    search += (i>1?" AND ":"") + '"' + rs.getString(i) + '"';
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return search;
    }
}
