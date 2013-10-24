package de.hsw.konsens.homer;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import de.hsw.konsens.homer.core.parser.HomerParser;
import de.hsw.konsens.homer.core.searchengine.ElasticsearchNode;
import de.hsw.konsens.homer.service.HomerService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SpringConfigTest {

	public static final String TEST_INDEX_NAME = "TEST";
	public static final String TEST_REPO_NAME = "TEST";

	static FileSystemXmlApplicationContext spring;

	@BeforeClass
	public void beforeClass() {
		try {
			spring = new FileSystemXmlApplicationContext("beans.xml");
		} catch (BeanCreationException e) {

		}
	}

	@Test
	public void elasticSearchTest() {
		try {
			ElasticsearchNode node = (ElasticsearchNode) spring
					.getBean("elasticsearch_local");
			Client client = node.client();
			AdminClient admin = client.admin();
			IndicesAdminClient indices = admin.indices();
			indices.create(new CreateIndexRequest(TEST_INDEX_NAME));
			indices.delete(new DeleteIndexRequest(TEST_INDEX_NAME));
		} catch (NoSuchBeanDefinitionException e) {
			Assert.assertFalse(true, "Bean can't be created.");
			throw new NotImplementedException();
		}
	}

	@Test
	public void sesameSailRepoTest() {
		Repository repository = (Repository) spring.getBean("sail_repository");
		try {
			repository.initialize();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(repository.isInitialized(),"Repository schould be initialized.");

		RepositoryConnection connection = null;
		try {
			connection = repository.getConnection();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(connection, "Connection should be initialized.");

		// TODO: Add Statements to TEST context
		// TODO: Get Statements from TEST context
		// TODO: Remove TEST context

//		try {
//			repository.shutDown();
//		} catch (RepositoryException e) {
//			e.printStackTrace();
//		}
//		Assert.assertFalse(repository.isInitialized(),
//				"Repository schould not be initialized anymore.");
	}

	@Test
	public void homerParserTest() {
		HomerParser homerParser = (HomerParser) spring.getBean("homer_parser");
		String result = homerParser.parse("${1+2}");
		Assert.assertEquals(result, "3","Wrong result.");		
	}

	@Test
	public void homerServiceTest() {
		HomerService homerService = (HomerService) spring.getBean("homerEmbeddedService");
		Assert.assertNotNull(homerService.getSearchengine());
		Assert.assertNotNull(homerService.getParser());
	}
	
	  @AfterClass
	  public void afterClass() {
		  spring.close();
	  }
}
