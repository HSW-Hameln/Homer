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
							+ "org.osgi.util.tracker; version=1.5.0");
			config.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA,
			// "org.apache.felix.eventadmin; version=1.3.2, "+
			// "org.osgi.service.event; version=1.3.2, "+
					"de.hsw.konsens.homer.generator; version=1.0.0 ");

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
