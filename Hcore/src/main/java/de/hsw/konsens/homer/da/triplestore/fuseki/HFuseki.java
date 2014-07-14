package de.hsw.konsens.homer.da.triplestore.fuseki;

import de.hsw.konsens.homer.da.HAbstractRemoteConnection;

import java.sql.*;

/**
 * Created by mielke on 01.04.2014.
 * <p>
 * Connection to a SPARQL Endpoint.
 */
public class HFuseki extends HAbstractRemoteConnection {

    private Connection conn = null;
    private ResultSet rs = null;
    private Statement stmt = null;

    /**
     * Default constructor opens JDBC SPARQL connection.
     */
    public HFuseki() {
        try {
            Class.forName("org.apache.jena.jdbc.remote.RemoteEndpointDriver");
            conn = DriverManager.getConnection("jdbc:jena:remote:query=http://localhost:3030/HOMER/query&update=http://localhost:3030/HOMER/update");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param query SPARQL Query.
     * @return Result set.
     * @see java.sql.ResultSet
     */
    public ResultSet query(String query) {

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Closes the JDBC connection.
     */
    public void close() {
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes all data from the repository.
     *
     * @return FALSE. Not implemented.
     */
    @Override
    public boolean clear() {
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("CLEAR ALL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * NOT IMPLEMENTED.
     *
     * @param rdf
     * @return
     */
    @Override
    public boolean insert(String... rdf) {
        return false;
    }

    /**
     * Tests if the SPARQL endpoint is available and queries can be executed.
     *
     * @return
     */
    @Override
    public boolean isConnected() {
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("CONSTRUCT { <dummy:status> <dummy:test> \"OK\"  } WHERE {} ");
            result.next();
            boolean r = result.getString(3).equals("OK");
            stmt.close();
            return r;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
