package de.hsw.konsens.homer.ontology;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceRegistration;

import de.hsw.konsens.homer.generator.GeneratorPlugin;
import de.hsw.konsens.homer.generator.GeneratorPluginManager;

public class PluginManagerImpl implements BundleActivator,GeneratorPluginManager,PluginManager {

	private BundleContext ctx = null;
	private ServiceRegistration<GeneratorPluginManager> registerationService;
	private List<GeneratorPlugin> pluginList = new ArrayList<GeneratorPlugin>();
	
	@Override
	public void start(BundleContext ctx) throws Exception {
		this.ctx = ctx;
		registerationService = ctx.registerService(GeneratorPluginManager.class, this,null);
	}

	@Override
	public void stop(BundleContext ctx) throws Exception {
		registerationService.unregister();
		this.ctx = null;

	}

	@Override
	public void shutdown() {
		try {
			ctx.getBundle().stop();
		} catch (BundleException e) {
			System.out.println("Failed to stop the Framework.");
			e.printStackTrace();
		}
	}

	@Override
	public List<GeneratorPlugin> getPlugins() {
		return pluginList;
	}

	@Override
	public void registerPlugin(GeneratorPlugin arg0) {
		pluginList.add(arg0);
	}

	@Override
	public void unregisterPlugin(GeneratorPlugin arg0) {
		pluginList.remove(arg0);
	}

}
