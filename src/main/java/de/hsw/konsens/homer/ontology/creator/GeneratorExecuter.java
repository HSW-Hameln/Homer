package de.hsw.konsens.homer.ontology.creator;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class GeneratorExecuter {
	private File generator ;

	public File getGenerator() {
		return generator;
	}

	public void setGenerator(File generator) {
		this.generator = generator;
	}
	
	public void exec()
	{
		if(isValid())
		{
			;
		}
		return;
	}
	
	private boolean loadSettings(){
		try {
			File f = new File(generator.getAbsolutePath()+"/generator.xml");
			Document doc  = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
			System.out.println(doc.getDocumentElement().getAttribute("class"));
			return true;
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isValid()
	{
		if(generator.isDirectory())
			if(loadSettings())
				return true;
		
		return false;
	}
}
