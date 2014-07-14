package de.hsw.konsens.homer.da;

/**
 * Created by mielke on 12.05.2014.
 *
 * HConnection is the Interface for all data sources.
 */
public interface HConnection {
    /**
     * This method deletes the configured data source.
     *
     * @return Status of the operation.
     */
    public boolean clear();

    /**
     * This method closes the connection.
     */
    public void close();

    /**
     *
     * @param data The type of the data changes depending on the type of the data source.
     * @return Status of the operation.
     */
    public boolean insert(String ... data);

    /**
     * This method tests a connection to the data source. The test sends a request to the configured repository.
     *
     * @return Value is true if connection test was successful.
     */
    public boolean isConnected();
}
