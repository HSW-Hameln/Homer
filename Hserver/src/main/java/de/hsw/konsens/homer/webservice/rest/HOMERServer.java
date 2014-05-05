package de.hsw.konsens.homer.webservice.rest;

import de.hsw.konsens.homer.webservice.rest.controller.HOMERController;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.restexpress.RestExpress;

/**
 * Created by mielke on 01.04.2014.
 *
 * HOMERServer is a singleton that controls the RESTful webservice. For building the server RestExpress is used.
 * You can find RestExpress at https://github.com/RestExpress/RestExpress.
 */
public class HOMERServer {

    /**
     * Singleton
     */
    private static HOMERServer instance = new HOMERServer();

    /**
     * Private constructor
     */
    private HOMERServer(){}

    /**
     *
     * @return HOMERServer Singleton.
     */
    public static HOMERServer getInstance(){
        return instance;
    }

    /**
     * Method that starts the Server.
     */
    public void start(){

        RestExpress server = new RestExpress()
                .setName("Hserver");

        HOMERController controller = new HOMERController();
        server.uri("/api/search", controller).action("search", HttpMethod.GET);

        server.bind();
        server.awaitShutdown();
    }

}
