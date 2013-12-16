package de.hsw.konsens.homer.core.rdf_store;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openrdf.model.impl.ValueFactoryImpl;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.springframework.beans.BeansException;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OntologyReader {

	private FileSystemXmlApplicationContext spring = new FileSystemXmlApplicationContext("beans.xml");
	
	public void importOntolgy(File dir){
		RepositoryConnection connection = null;
		try {
			 Repository repo = spring.getBean(SesameRepositoryConnection.class).getRepository();
			 repo.initialize();
			connection =		repo.getConnection();
		} catch (BeansException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(dir.isDirectory())
		{
			File content = new File(dir.getAbsolutePath()+System.getProperty("file.separator")+"content.xml");
			if(content.exists())
			{
				try {
					Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(content);
									
					Element element = doc.getElementById("data");
					NodeList data = element.getElementsByTagName("file");
					NamedNodeMap n;
					for(int i = 0 ; i < data.getLength() ; i++)
					{
						n = data.item(i).getAttributes();
						connection.add(new File(dir.getAbsolutePath()+System.getProperty("file.separator")+n.getNamedItem("file").getNodeValue()), n.getNamedItem("base").getNodeValue(), RDFFormat.TURTLE,new ValueFactoryImpl().createURI(n.getNamedItem("context").getNodeValue()));
					}
						
					connection.commit();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RDFParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
