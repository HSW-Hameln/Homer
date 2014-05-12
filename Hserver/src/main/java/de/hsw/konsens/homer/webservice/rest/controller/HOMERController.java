package de.hsw.konsens.homer.webservice.rest.controller;

import de.hsw.konsens.homer.HOMERConstants;
import de.hsw.konsens.homer.Hserver;
import org.restexpress.Request;
import org.restexpress.Response;

import java.util.Map;

/**
 * Created by mielke on 01.04.2014.
 */
public class HOMERController {
    public Map search(Request req, Response rsp){
        return Hserver.getInstance().search(req.getBodyFromUrlFormEncoded().get(HOMERConstants.SEARCH_REQUEST).get(0));
    }
}
