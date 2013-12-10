package de.hsw.konsens.homer;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import de.hsw.konsens.homer.client.HomerClient;
import de.hsw.konsens.homer.client.HomerClientLocal;
import de.hsw.konsens.homer.service.HomerService;

/**
 * Main class of the HOMER Framework. This class contains the Main method and methods for starting and stopping the framework.
 * 
 * @author mielke
 *
 */
public class Homer {

	private FileSystemXmlApplicationContext spring = new FileSystemXmlApplicationContext("beans.xml");
	private HomerService service = null;
	
	/**
	 * This method return a HomerClientLocal and starts the HOMER-Service if necessary.
	 */
	public HomerClient getClient() {
		if(!isRunning())
			start();
		return new HomerClientLocal(service);
	}

	/**
	 * This method starts the REST-full-HOMER-Service.
	 */
	public void startWebservice() {
		//TODO: Startup webservice to access SERVICE OR CLIENT ?!?
	}

	/**
	 * This method starts the HOMER-Service.
	 */
	public void start() {
		service = spring.getBean(HomerService.class);
	}

	public boolean isRunning() {
		return service != null;
	}

	public void stop() {
		service.getElasticsearch().close();
		service.getOpenRDFSesame().shutDown();
		service.getPluginManager().shutdown();
		service = null;
	}

	public static void main(String[] args) {
		Homer homer = new Homer();
		homer.startWebservice();
	}

}
