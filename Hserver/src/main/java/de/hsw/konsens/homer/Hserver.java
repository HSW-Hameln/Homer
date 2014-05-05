package de.hsw.konsens.homer;

import de.hsw.konsens.homer.webservice.rest.HOMERServer;

import java.util.Map;

/**
 * Created by mielke on 01.04.2014.
 *
 * This Class is the facade for the Hserver framework and contains the main() method for the Standalone service.
 */
public class Hserver {

    /**
     * Singleton
     */
    private static Hserver instance = new Hserver();

    /**
     * Private constructor
     */
    private Hserver(){}

    /**
     *
     * @return HOMERServer Singleton.
     */
    public static Hserver getInstance(){
        return instance;
    }

    /**
     *
     * @param HOMERQuery
     * @return Returns a Map that contains the search results. As well as some information about the expansion and the search process.
     */
    public Map<String,String> search(String HOMERQuery){
        return new HOMER().search(HOMERQuery);
    }

    /**
     *
     * Main method for Hserver Standalone:
     * Commandline arguments -configuration <PATH_TO_FILE>
     *
     */
    public static void main(String[] args){
        HOMERServer.getInstance().start();
    }
}
