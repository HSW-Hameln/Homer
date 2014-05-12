package de.hsw.konsens.homer.da.triplestore.fuseki;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mielke on 17.04.2014.
 */
public class HFusekiTest {
    @Test
    public void connectionTest() throws SQLException {
        HFuseki fuseki = new HFuseki();
        ResultSet r
                = fuseki.query("CONSTRUCT { <dummy:status> <dummy:test> \"OK\"  } \n" +
                "WHERE {} ");

        r.next();
        Assert.assertEquals("OK",r.getString(3));
    }
}
