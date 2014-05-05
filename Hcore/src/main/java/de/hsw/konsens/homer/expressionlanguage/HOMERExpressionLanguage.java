package de.hsw.konsens.homer.expressionlanguage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;

/**
 * Created by mielke on 01.04.2014.
 */
public class HOMERExpressionLanguage {
    @Autowired
    private ExpressionParser parser;
    @Autowired
    private ParserContext parserContext;
    @Autowired
    private EvaluationContext evaluationContext;

    public ExpressionParser getParser() {
        return parser;
    }

    public String parse(String expressionString) {
        return this.parser.parseExpression(expressionString, parserContext)
                .getValue(evaluationContext, String.class);
    }
}
