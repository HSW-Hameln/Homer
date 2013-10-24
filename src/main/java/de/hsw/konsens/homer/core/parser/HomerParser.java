package de.hsw.konsens.homer.core.parser;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;

public class HomerParser {
	private ExpressionParser parser;
	private ParserContext parserContext;
	private EvaluationContext evaluationContext;

	public ExpressionParser getParser() {
		return parser;
	}

	public void setParser(ExpressionParser parser) {
		this.parser = parser;
	}

	public ParserContext getParserContext() {
		return parserContext;
	}

	public void setParserContext(ParserContext parserContext) {
		this.parserContext = parserContext;
	}

	public EvaluationContext getEvaluationContext() {
		return evaluationContext;
	}

	public void setEvaluationContext(EvaluationContext evaluationContext) {
		this.evaluationContext = evaluationContext;
	}

	public String parse(String expressionString) {
		return this.parser.parseExpression(expressionString, parserContext)
				.getValue(evaluationContext, String.class);
	}

}
