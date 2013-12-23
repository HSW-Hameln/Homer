package de.hsw.konsens.homer;

import java.util.List;

import org.junit.Assert;
import org.testng.annotations.Test;

import de.hsw.konsens.homer.client.HomerClient;


public class HomerTest {

	Homer homer = null;

	@Test
	public void Homer() {
		homer = new Homer();
		Assert.assertNotNull(homer);
	}

	@Test(dependsOnMethods = { "Homer" })
	public void getClient() {
		HomerClient client = homer.getClient();
		Assert.assertNotNull(client);
	}
	
	@Test(dependsOnMethods = { "getClient" })
	public void searchForOntology(){
		HomerClient client = homer.getClient();
		List<String> r = client.search("sail","michael");
		for ( String sh : r) {
			System.out.println(sh);
		}
	}

	@Test(dependsOnMethods = { "getClient" })
	public void searchForDocuments(){
		HomerClient client = homer.getClient();
		List<String> r = client.search("data","müller");
		for ( String sh : r) {
			System.out.println(sh);
		}
	}
	
//	@Test(dependsOnMethods = { "getClient" })
//	public void searchForDocumentsExtended(){
//		HomerClient client = homer.getClient();
//		List<String> r = client.search("sail","${sparql('SELECT ?l WHERE{?s rdfs:label ?l}')}");
//		for ( String sh : r) {
//			System.out.println(":"+sh);
//		}
//	}
}
