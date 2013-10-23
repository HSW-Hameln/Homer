package de.hsw.konsens.homer;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.annotations.BeforeClass;

import de.hsw.konsens.homer.service.HomerService;

public class StartupTestEmbedded {

	HomerService homer;

	static FileSystemXmlApplicationContext spring;
	
	@BeforeClass
	public void beforeClass() {
		try{
			spring =  new FileSystemXmlApplicationContext("beans.xml");
			homer = (HomerService) spring.getBean("homerEmbeddedService");
		}catch (BeanCreationException e) {
			
		}
	}
}
