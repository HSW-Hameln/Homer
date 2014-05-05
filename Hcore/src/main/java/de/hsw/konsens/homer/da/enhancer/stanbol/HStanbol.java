package de.hsw.konsens.homer.da.enhancer.stanbol;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * Created by mielke on 01.04.2014.
 */
public class HStanbol {
    public HStanbol(){
        WebResource res = Client.create().resource("http://localhost:8080/enhancer");
        String ret = res.accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46YWRtaW4=")
                .post(String.class, "The Stanbol enhancer can detect famous cities such as Paris and people such as Bob Marley.");

        System.out.println(ret);

    }
}
