package de.hsw.konsens.homer.expressionlanguage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by mielke on 08.04.2014.
 */
public class HOMERExpressionLanguageTest {
    @Test
    public void springTest(){
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();

        ctx.register(HOMERExpressionLanguageConfig.class,HOMERExpressionLanguage.class);
        ctx.refresh();

        HOMERExpressionLanguage parser = ctx.getBean(HOMERExpressionLanguage.class);

        Assert.assertEquals("3",parser.parse("${1+2}"));
    }
}
