package de.hsw.konsens.homer.ontology;

import java.util.Map;
import java.util.Properties;

import org.osgi.framework.BundleActivator;

public interface GeneratorPlugin extends BundleActivator{
	public String getContext();
	public Map<String,String> generate();
	public Properties getProperties();
	public void setProperties(Properties properties);
}
