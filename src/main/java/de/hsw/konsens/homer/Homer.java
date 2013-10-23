package de.hsw.konsens.homer;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import de.hsw.konsens.homer.service.HomerService;

public class Homer {
	
	public enum Access {
		LOCAL,REST,
	}

	static FileSystemXmlApplicationContext spring = new FileSystemXmlApplicationContext("beans.xml");
	
	public static HomerService startHomer(Access ws){
		switch (ws) {
		case LOCAL:
			return (HomerService) spring.getBean("homerEmbeddedService");
		case REST:
			return (HomerService) spring.getBean("homerEmbeddedService");
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
			startHomer(Access.REST);
		}
	}
}
