package de.hsw.konsens.homer;

import org.junit.Assert;
import org.testng.annotations.Test;


public class HomerTest {

	Homer homer = null;

	@Test
	public void Homer() {
		homer = new Homer();
		Assert.assertNotNull(homer);
	}

	@Test(dependsOnMethods = { "Homer" })
	public void getClient() {
		Assert.assertNotNull(homer.getClient());
	}

}
