package de.hsw.konsens.homer.ontology.creator;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class GeneratorExecuterTest {
	
	GeneratorExecuter ge = new GeneratorExecuter();
	
  @BeforeClass
  public void beforeClass() {
	  ge.setGenerator(new File("src/main/java/resources/generators/hsw_mitarbeiter"));
  }

  @AfterClass
  public void afterClass() {
  }


  @Test
  public void exec() {

  }

  @Test
  public void getGenerator() {
  }

  @Test
  public void isValid() {
	  Assert.assertTrue(ge.isValid(),"Fehler");
  }

  @Test
  public void loadSettings() {
  }

  @Test
  public void setGenerator() {
  }
}
