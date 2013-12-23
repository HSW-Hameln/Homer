package de.hsw.konsens.homer.ontology;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.felix.framework.Felix;
import org.apache.felix.framework.util.FelixConstants;
import org.apache.felix.main.AutoProcessor;
import org.osgi.framework.Constants;

public class PluginManagerFactory {

	public PluginManager getPluginManagerInstance() {
		return startFelix();
	}

	private PluginManager startFelix() {
		System.out.println("Prepare to start Host Aplication");

		Map<String, Object> config = new HashMap<String, Object>();
		PluginManager pluginManager = new PluginManagerImpl();
		config.put(FelixConstants.SYSTEMBUNDLE_ACTIVATORS_PROP,
				Arrays.asList(pluginManager));

		try {
			System.out.println("Starting felix ...");
			config.put(FelixConstants.FRAMEWORK_SYSTEMPACKAGES,
					"org.osgi.framework; version=1.7.0,"
							+ "org.osgi.util.tracker; version=1.5.0,"
//							+ "javax.*; version=0.0.0,"
//							+ "prg w3c.*; version=0.0.0"
					
//							+ "org.w3c.dom; version=0.0.0,"
//							+ "org.w3c.dom.css; version=0.0.0,"
//							+ "org.w3c.dom.stylesheets; version=0.0.0,"
							+ "org.ietf.jgss; version=0.0.0,"
							+ "javax.imageio; version=0.0.0,"
							+ "javax.management; version=0.0.0,"
							+ "javax.naming; version=0.0.0,"
							+ "javax.naming.spi; version=0.0.0,"
							+ "javax.net; version=0.0.0,"
							+ "javax.net.ssl; version=0.0.0,"
							+ "javax.security.auth.x500; version=0.0.0,"
							+ "javax.sql; version=0.0.0,"
							+ "javax.swing; version=0.0.0,"
							+ "javax.swing.border; version=0.0.0,"
							+ "javax.swing.event; version=0.0.0,"
							+ "javax.swing.text; version=0.0.0,"
							+ "javax.swing.table; version=0.0.0,"
							+ "javax.swing.tree; version=0.0.0,"
//							+ "javax.xml.namespace; version=0.0.0,"
							+ "javax.xml.stream; version=0.0.0,"
							+ "javax.xml.stream.events; version=0.0.0,"
//							+ "javax.xml.transform; version=0.0.0,"
//							+ "javax.xml.transform.sax; version=0.0.0,"
//							+ "javax.xml.transform.stream; version=0.0.0,"
//							+ "javax.xml.transform.dom; version=0.0.0,"
//							+ "javax.xml.xpath; version=0.0.0,"
//							+ "javax.xml.parsers; version=0.0.0,"
							+ "javax.crypto; version=0.0.0,"
							+ "javax.crypto.spec; version=0.0.0,"
							
							
//							+ "org.apache.xml.serialize; version=2.9.1,"
//													
//							+ "org.apache.xerces.parsers; version=2.9.1,"
//							+ "org.apache.xerces.util; version=2.9.1,"
//							+ "org.apache.xerces.xs; version=2.9.1,"
//							+ "org.apache.xerces.xni; version=2.9.1,"
//							+ "org.apache.xerces.xni.parser; version=2.9.1,"
//							+ "org.apache.xerces.xni.grammars; version=2.9.1,"
//
//							+ "org.apache.xerces.impl; version=2.9.1,"
//							+ "org.apache.xerces.impl.dv; version=2.9.1,"
//							+ "org.apache.xerces.impl.dv.xs; version=2.9.1,"						
//							+ "org.apache.xerces.impl.dv.util; version=2.9.1,"
//							+ "org.apache.xerces.impl.validation; version=2.9.1,"
//							+ "org.apache.xerces.impl.xpath.regex; version=2.9.1,"
						
							+ "javax.imageio.stream; version=0.0.0");
			config.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA,
			// "org.apache.felix.eventadmin; version=1.3.2, "+
			// "org.osgi.service.event; version=1.3.2, "+
					"de.hsw.konsens.homer.generator; version=1.0.0");

			Felix felix = new Felix(config);
			felix.init();

			Properties confprop = new Properties();
			confprop.put(AutoProcessor.AUTO_DEPLOY_ACTION_PROPERY,
					"install,start,update,uninstall");
			AutoProcessor.process(confprop, felix.getBundleContext());

			felix.start();
		} catch (Exception ex) {
			System.err.println("Could not create framework: " + ex);
			ex.printStackTrace();
		}
		return pluginManager;
	}
}
