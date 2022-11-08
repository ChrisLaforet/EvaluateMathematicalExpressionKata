import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathEvaluator {

  public double calculate(String expression) {
	  return ExpressionParser.parseAndEvaluate(expression);
  }

  
  static abstract class Expression {
	  public abstract double evaluate();
  }
  
  static abstract class OperationType {
	  public abstract boolean matchesSymbol(String symbol);
	  public abstract double evaluate(double lhs, double rhs);
  }
  
  static class Value extends Expression {
	  private double value;
	  
	  public Value(String value) {
		  this.value = Double.parseDouble(value);
	  }
	  
	  @Override
	  public double evaluate() {
		  return value;
	  }
  }
  
  static class Multiplier extends OperationType {

	@Override
	public boolean matchesSymbol(String symbol) {
		return "*".equals(symbol);
	}

	@Override
	public double evaluate(double lhs, double rhs) {
		return lhs * rhs;
	}
  }
  
  static class Divider extends OperationType {

	@Override
	public boolean matchesSymbol(String symbol) {
		return "/".equals(symbol);
	}

	@Override
	public double evaluate(double lhs, double rhs) {
		return lhs / rhs;
	}
  }
  
  static class Adder extends OperationType {

	@Override
	public boolean matchesSymbol(String symbol) {
		return "+".equals(symbol);
	}

	@Override
	public double evaluate(double lhs, double rhs) {
		return lhs + rhs;
	}
  }
  
  static class Subtractor extends OperationType {

	@Override
	public boolean matchesSymbol(String symbol) {
		return "-".equals(symbol);
	}

	@Override
	public double evaluate(double lhs, double rhs) {
		return lhs - rhs;
	}
  }

  static class Operation extends Expression {
	  private Expression lhs;
	  private Expression rhs;
	  private OperationType optype;
	  
	  public Operation(Expression lhs, Expression rhs, OperationType operationType) {
		  this.lhs = lhs;
		  this.rhs = rhs;
		  this.optype = operationType;
	  }
	  
	  @Override
	  public double evaluate() {
		 return optype.evaluate(lhs.evaluate(), rhs.evaluate());
	  }
  }
  
  static abstract class Token {
	  public abstract String getToken();
  }
  
  static class Number extends Token {
	  private String value;
	  
	  public Number(String value) {
		  this.value = value;
	  }

	@Override
	public String getToken() {
		return value;
	}
  }
  
  static class Operator extends Token {
	  private String value;
	  
	  public Operator(String value) {
		  this.value = value;
	  }

	@Override
	public String getToken() {
		return value;
	}
  }
  
  static class OpenParen extends Token {
	@Override
	public String getToken() {
		return "(";
	}
  }

  static class CloseParen extends Token {
	@Override
	public String getToken() {
		return ")";
	}
  }
  
  
  private static List<OperationType> operations = Arrays.asList(new Adder(), new Subtractor(), new Multiplier(), new Divider());
  
  
  static class Tokenizer {
	  private int offset = 0;
	  private String expression;
	  private Token lastToken;
	  private Token currentToken;
	  
	  public Tokenizer(String expression) {
		  this.expression = expression;
	  }
	  
	  public Token emitCurrent() {
		  return currentToken;
	  }
	  
	  public Token emitLast() {
		  return lastToken;
	  }
	  
	  public Token emitNext() {
		  while (offset < expression.length()) {
			  char ch = expression.charAt(offset++);
			  if (Character.isSpaceChar(ch)) {
				  continue;
			  }
			  
			  if (Character.isDigit(ch)) {
				  Token token = emitNumber(ch);
				  lastToken = currentToken;
				  currentToken = token;
				  return currentToken;
			  } else if (isOperation(ch)) {
				  Token token = new Operator(Character.toString(ch));
				  lastToken = currentToken;
				  currentToken = token;
				  return currentToken;
			  }
			  
		  }
		  return null;
	  }
	  
	  private boolean isOperation(char ch) {
		  for (OperationType operationType : operations) {
			  if (operationType.matchesSymbol(Character.toString(ch))) {
				  return true;
			  }
		  }
		  return false;
	  }
	  
	  private Token emitNumber(char firstCharacter) {
		  StringBuilder sb = new StringBuilder();
		  sb.append(firstCharacter);
		  boolean isExponent = false;
		  char lastCh = ' ';
		  while (offset < expression.length()) {
			  char ch = expression.charAt(offset);
			  if (Character.isDigit(ch)) {
				  sb.append(ch);
				  ++offset;
			  } else if (ch == '.') {		// what about countries that use , instead of .) {
				  if (isExponent) {
					  break;
				  }
				  sb.append(ch);
				  ++offset;
			  } else if (ch == 'E' || ch == 'e') {
				  if (isExponent) {
					  break;
				  }
				  isExponent = true;
				  sb.append(ch);
				  ++offset;
			  } else if (ch == '+' || ch == '-') {
				  if (!isExponent || (lastCh != 'E' && lastCh != 'e')) {
					  break;
				  }
				  sb.append(ch);
				  ++offset;
			  } else {
				  break;
			  }
			  lastCh = ch;
			  
		  }

		  return new Number(sb.toString());
	  }
  }

  
  static class ExpressionParser {
	  
	  // Note must handle unary minus
	  
	  public static double parseAndEvaluate(String expressionString) {
		  Expression expression = tokenize(expressionString);
		  return expression == null ? null : expression.evaluate();
	  }
	  
	  private static Expression tokenize(String expressionString) {
		  Tokenizer tokenizer = new Tokenizer(expressionString);
		  Expression expression = null;
		  while (true) {
			  Token token = tokenizer.emitNext();
			  if (token == null) {
				  break;
			  }
			  
			  if (token instanceof Number) {
				  expression = new Value(token.getToken());
			  } else if (token instanceof Operator) {
				  
			  }
		  }
		  
		  return expression;
	  }
	  
	  
	  
  }
  
}
