package de.hsw.konsens.homer.da;

/**
 * Created by mielke on 12.05.2014.
 */
public interface HConnection {
    public boolean clear();
    public boolean insert(String ... data);
    public boolean isConnected();
}
