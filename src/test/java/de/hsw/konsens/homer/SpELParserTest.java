package de.hsw.konsens.homer;

import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import de.hsw.konsens.homer.core.parser.HomerParser;

public class SpELParserTest {


private HomerParser homerParser;
private FileSystemXmlApplicationContext spring;

@BeforeClass
  public void beforeClass() {
	spring = new FileSystemXmlApplicationContext("beans.xml");
	homerParser = (HomerParser) spring.getBean("homer_parser");
  }

  @Test
  public void parse() {
		
	  System.out.println(homerParser.parse("${samePredicate('"
	  		+ "<http://hsw-hameln.de/hsw/sven-mielke.php>','<http://www.hsw-hameln.de/semantics/vocabulary/emloyee#department>')}"));
	  System.out.println(homerParser.parse(""
	  		+ "${"
	  		+ "sparql('"
	  		+ "SELECT ?l WHERE {<http://hsw-hameln.de/hsw/sven-mielke.php> <http://www.hsw-hameln.de/semantics/vocabulary/emloyee#department> ?o. "
	  		+ "?s ?p ?o. "
	  		+ "?s rdfs:label ?l}"
	  		+ "')"
	  		+ "}"));


  }
  
  @AfterClass
  public void afterClass() {
	  spring.close();
  }
}
