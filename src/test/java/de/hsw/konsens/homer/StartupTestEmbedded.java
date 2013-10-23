package de.hsw.konsens.homer;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import de.hsw.konsens.homer.client.HomerClient;
import de.hsw.konsens.homer.service.HomerService;

public class StartupTestEmbedded {

	HomerService homer;

	@BeforeClass
	public void beforeTest() {

	}

	@Test
	public void embeddedTest() {
		homer = Homer.startHomer(Homer.Webservice.NONE);
	}

	@Test(dependsOnMethods = "embeddedTest")
	public void clientTest() {
		HomerClient client = new HomerClient();
		client.setService(homer);
		client.status();
	}
}
