package de.hsw.konsens.homer.core.rdf_store;

import java.io.File;

import org.testng.annotations.Test;

public class OntologyReaderTest {

  @Test
  public void importOntolgy() {
	  new OntologyReader().importOntolgy(new File("U:\\git\\Homer\\src\\main\\resources\\ontologies\\hsw"));
  }
}
