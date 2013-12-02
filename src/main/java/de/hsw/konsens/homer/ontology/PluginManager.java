package de.hsw.konsens.homer.ontology;

import java.util.List;

import org.osgi.framework.BundleActivator;

public interface PluginManager extends BundleActivator{
	public void addPlugin(GeneratorPlugin generatorPlugin);
	public List<GeneratorPlugin> getPlugins();
	public void removePlugin(GeneratorPlugin generatorPlugin);
	public void shutdown();
	public void close();
}
