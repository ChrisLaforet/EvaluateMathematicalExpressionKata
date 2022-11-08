import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathEvaluator {

  public double calculate(String expression) {
	  return new Value(expression).evaluate();
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
	  
	  public double evaluate() {
		 return optype.evaluate(lhs.evaluate(), rhs.evaluate());
	  }
  }
  
  static abstract class Token {
	  public abstract String getToken();
  }
  
  static class Parser {
	  
	  private List<OperationType> operations = Arrays.asList(new Adder(), new Subtractor(), new Multiplier(), new Divider());
	  
	  // Note must handle unary minus
	  
	  
	  
  }
  
}
