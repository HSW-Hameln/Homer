package de.hsw.konsens.homer;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonTest {
  @Test(groups="cleanup")
  public void removeLocalSailDir() {
	  File dir = new File(HOMERTestConstants.SAIL_DIR);
	  try {
		FileUtils.deleteDirectory(dir);
		Assert.assertFalse(dir.exists(), HOMERTestConstants.SAIL_DIR+" dir not deleted.");
	} catch (IOException e) {
		e.printStackTrace();
		Assert.assertFalse(true);		
	}
  }
  
  @Test(groups="cleanup")
  public void removeLocalElasticSearchDir() {
	  File dir = new File(HOMERTestConstants. ELASTICSEARCH_DIR);
	  try {
		FileUtils.deleteDirectory(dir);
		Assert.assertFalse(dir.exists(), HOMERTestConstants. ELASTICSEARCH_DIR+" dir not deleted.");
	} catch (IOException e) {
		e.printStackTrace();
		Assert.assertFalse(true);		
	}
  }
}
