package de.hsw.konsens.homer.expressionlanguage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by mielke on 08.04.2014.
 */
@Configuration
public class HOMERExpressionLanguageConfig {
    @Bean
    public SpelExpressionParser spelExpressionParser(){
        return new SpelExpressionParser();
    }
    @Bean
    public TemplateParserContext templateParserContext(){
        return new TemplateParserContext("${","}");
    }
    @Bean
    public StandardEvaluationContext standardEvaluationContext(){
        return new StandardEvaluationContext(new HOMERExpressionLanguageCommands());
    }
}
