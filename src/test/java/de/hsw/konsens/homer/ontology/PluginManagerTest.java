package de.hsw.konsens.homer.ontology;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import de.hsw.konsens.homer.generator.GeneratorPlugin;
import de.hsw.konsens.homer.generator.GeneratorPluginManager;

public class PluginManagerTest {
	
	PluginManager testObj = null;
	
  @BeforeClass
  public void beforeClass() {
	  testObj = new PluginManagerFactory().getPluginManagerInstance();
  }


  @Test
  public void shutdown() throws IOException {
	  
	  List<GeneratorPlugin> plugins = ((GeneratorPluginManager) testObj).getPlugins();
	  
	  for(GeneratorPlugin p : plugins){
		  p.setEnabled(true);
		  p.runGenerator(new File("test-output"));
	  }
	  
	  testObj.shutdown();
  }
}
