package de.hsw.konsens.homer.da.searchengine.elasticsearch;

import org.elasticsearch.action.admin.cluster.node.info.NodeInfo;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoRequest;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.util.concurrent.ExecutionException;

/**
 * Created by mielke on 01.04.2014.
 *
 * Configuration
 *      cluster.name
 *      TransportAddress of a known Node
 */
public class HElasticsearch {
    public HElasticsearch(){

        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "myClusterName").build();
        Client client =    new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress("localhost", 9300));

        //ToDo: Test if nodes were found.

        try {
            NodesInfoResponse res = client.admin().cluster().nodesInfo(new NodesInfoRequest()).get();
            System.out.println(res.getNodes().length + "nodes");
            for(NodeInfo n : res.getNodes()){
                System.out.println(n.getNode().getName());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        client.close();
    }
}
