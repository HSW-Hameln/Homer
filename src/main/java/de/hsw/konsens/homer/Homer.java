package de.hsw.konsens.homer;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import de.hsw.konsens.homer.service.HomerService;

public class Homer {
	
	public enum Webservice {
		NONE,REST,
	}

	static FileSystemXmlApplicationContext spring = new FileSystemXmlApplicationContext("beans.xml");
	
	public static HomerService startHomer(Webservice ws){
		switch (ws) {
		case NONE:
			return (HomerService) spring.getBean("homerEmbeddedService");
		case REST:
			throw new NotImplementedException();
		default:
			throw new IllegalArgumentException("The value "+ws+" is unknown");
		}
	}
	
	/**
	 * This method is the main method. It starts a the HOMER framework in standalone mode. You can pass a path of a cofiguration file as commandline parameter.
	 * 
	 * @param args Commandline arguments
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			startHomer(Webservice.REST);
		}
	}
}
