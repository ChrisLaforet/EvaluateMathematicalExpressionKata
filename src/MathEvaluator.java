public class MathEvaluator {

  public double calculate(String expression) {
	  return new Value(expression).evaluate();
  }

  
  static abstract class Expression {
	  public abstract double evaluate();
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
  
  static class Parser {
	  
  }
  
}
