package de.hsw.konsens.homer;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.node.Node;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SpringConfigTest {

	public static final String TEST_INDEX_NAME ="TEST";
	public static final String TEST_REPO_NAME  ="TEST";
	
	
	static FileSystemXmlApplicationContext spring;
	
	@BeforeClass
	public void beforeClass() {
		try{
			spring =  new FileSystemXmlApplicationContext("beans.xml");
		}catch (BeanCreationException e) {
			
		}
	}
	
	@Test
	public void elasticSearchTest() {
		try{
			Node node = (Node) spring.getBean("elasticsearch_node");
			Client client = node.client();
			AdminClient admin =client.admin();
			IndicesAdminClient indices = admin.indices();
			indices.create(new CreateIndexRequest(TEST_INDEX_NAME));
			indices.delete(new DeleteIndexRequest(TEST_INDEX_NAME));
		}catch(NoSuchBeanDefinitionException e) {
			Assert.assertFalse(true,"Bean can't be created.");
			throw new NotImplementedException();
		}
	}

	@Test
	public void sesameSailRepoTest() {
		spring.getBean("sail_repository");
	}

	@Test
	public void sesameHTTPRepoTest() {
		spring.getBean("http_repository");
	}

	
}
