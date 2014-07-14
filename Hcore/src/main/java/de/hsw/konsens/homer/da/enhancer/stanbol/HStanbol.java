package de.hsw.konsens.homer.da.enhancer.stanbol;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import de.hsw.konsens.homer.HOMERConfig;
import de.hsw.konsens.homer.da.HAbstractRemoteConnection;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * Created by mielke on 01.04.2014.
 * <p>
 * This class should be a connection to an Apache Stanbol instance. At the moment Stanbol is not used by the Framework.
 */
public class HStanbol extends HAbstractRemoteConnection {
    public HStanbol() {
        WebResource res = Client.create().resource(HOMERConfig.URL_STANBOL_ENDPOINT);
        String ret = res.accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46YWRtaW4=")
                .post(String.class, "The Stanbol enhancer can detect famous cities such as Paris and people such as Bob Marley.");

        System.out.println(ret);

    }

    @Override
    public boolean clear() {
        //ToDo: Implement Clear
        return false;
    }

    @Override
    public boolean insert(String... data) {
        return false;
    }

    @Override
    public boolean isConnected() {
        //ToDo: Implement connection Test
        return false;
    }
}
