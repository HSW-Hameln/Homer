package de.hsw.konsens.homer.da.triplestore.fuseki;

import java.sql.*;

/**
 * Created by mielke on 01.04.2014.
 *
 * Configuration
 *      SPARQL Endpoint Host:PORT
 *      dataset
 */
public class HFuseki {
    public HFuseki(){
        try {
            Class.forName("org.apache.jena.jdbc.remote.RemoteEndpointDriver");
            Connection conn = DriverManager.getConnection("jdbc:jena:remote:query=http://localhost:3030/books/query&update=http://localhost:3030/books/update");
            Statement stmt = conn.createStatement();
            //ToDo: Test if connection is established.
            ResultSet rs = stmt.executeQuery("SELECT ?s ?p ?o WHERE {?s ?p ?o}");
            while( rs.next() ) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    System.out.print(rs.getString(i));
                System.out.println();
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
