package de.hsw.konsens.homer;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import de.hsw.konsens.homer.Homer.Access;
import de.hsw.konsens.homer.service.HomerService;

public class StartupTestEmbedded {

	HomerService homer;
	
	@BeforeClass
	public void beforeClass() {
		homer = Homer.startHomer(Access.LOCAL);
	}
	
	@Test
	public void initTest()
	{
		Assert.assertTrue(true);
	}
}
