package de.hsw.konsens.homer;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import de.hsw.konsens.homer.client.HomerClient;
import de.hsw.konsens.homer.service.HomerService;

public class StartupTestWebservice {

	HomerService homer;

	@BeforeClass
	public void beforeTest() {

	}

	@Test
	public void webserviceTest() {
		Homer.startHomer(Homer.Webservice.REST);
	}

	@Test(dependsOnMethods = "standaloneTest")
	public void clientTest() {
		HomerClient client = new HomerClient();
		client.setService(homer);
		client.status();
	}
}
