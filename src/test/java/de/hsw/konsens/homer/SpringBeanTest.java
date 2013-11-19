package de.hsw.konsens.homer;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.node.Node;
import org.openrdf.model.Resource;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import de.hsw.konsens.homer.core.parser.HomerParser;
import de.hsw.konsens.homer.service.HomerService;

public class SpringBeanTest {

	public static final String TEST_INDEX_NAME = "TEST";
	public static final String TEST_REPO_NAME = "TEST";

	static FileSystemXmlApplicationContext spring;

	@BeforeClass
	public void beforeClass() {
		try {
			spring = new FileSystemXmlApplicationContext("beans.xml");

			try {
				FileUtils
						.deleteDirectory(new File(HOMERTestConstants.SAIL_DIR));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Assert.assertTrue(dir.delete(),"Should be deleted");
		} catch (BeanCreationException e) {

		}
	}

	@Test
	public void elasticSearchTest() {
		try {
			Node node = (Node) spring
					.getBean("elasticsearch_local");
			Client client = node.client();
			AdminClient admin = client.admin();
			IndicesAdminClient indices = admin.indices();
			indices.create(new CreateIndexRequest(TEST_INDEX_NAME));
			indices.delete(new DeleteIndexRequest(TEST_INDEX_NAME));
			client.close();
			node.close();
		} catch (NoSuchBeanDefinitionException e) {
			Assert.assertFalse(true, "Bean can't be created.");
		}
	}

	@Test
	public void sesameSailCleanTest() {
		Repository repository = (Repository) spring.getBean("sail_repository");
		try {
			repository.initialize();
			Assert.assertTrue(repository.isInitialized(),
					"Repository schould be initialized.");
			RepositoryConnection con = repository.getConnection();
			con.add(new File("build/resources/test/turtle/hsw_mitarbeiter.ttl"),
					"", RDFFormat.TURTLE, new Resource[] {});
			con.commit();

			Assert.assertTrue(repository.isInitialized(),
					"Repository schould be initialized.");

			Assert.assertNotNull(con, "Connection should be initialized.");

			// TODO: Add Statements to TEST context
			// TODO: Get Statements from TEST context
			// TODO: Remove TEST context

			con.close();
			repository.shutDown();
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (RDFParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertFalse(repository.isInitialized(),
				"Repository schould not be initialized anymore.");

	}

	@Test
	public void homerParserTest() {
		HomerParser homerParser = (HomerParser) spring.getBean("homer_parser");
		String result = homerParser.parse("${1+2}");
		Assert.assertEquals(result, "3", "Wrong result.");
	}

	@Test
	public void homerServiceTest() {
		HomerService homerService = (HomerService) spring
				.getBean("homerEmbeddedService");
		Assert.assertNotNull(homerService.getSearchengine());
		Assert.assertNotNull(homerService.getParser());
	}

	@AfterClass
	public void afterClass() {
		spring.close();
	}
}
