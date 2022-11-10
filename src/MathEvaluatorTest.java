import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MathEvaluatorTest {

	// Additional tests 
	
	@Test
	public void givenExpressionOfInteger_whenValueIs1_thenReturnsValueAsDouble() {
		assertEquals(1.0, new MathEvaluator().calculate("1"));
	}
	

	@Test
	public void givenExpressionOfInteger_whenValueIs2_thenReturnsValueAsDouble() {
		assertEquals(2.0, new MathEvaluator().calculate("2"));
	}
	
	@Test
	public void givenExpressionOfDouble_whenValueIncludesDecimal_thenReturnsValueAsDouble() {
		assertEquals(2.12345, new MathEvaluator().calculate("2.12345"));
	}
	
	@Test
	public void givenExpressionOfDouble_whenValueIncludesExponent_thenReturnsValueAsDouble() {
		assertEquals(2.12E12, new MathEvaluator().calculate("2.12E12"));
	}
	
	@Test
	public void givenExpressionOfNumberPlusNumber_whenParsed_thenReturnsCalculatedExpression() {
		assertEquals(3.0, new MathEvaluator().calculate("1+2"));
	}

	@Test
	public void givenExpressionOfParentheticalNumberPlusNumber_whenParsed_thenReturnsCalculatedExpression() {
		assertEquals(3.0, new MathEvaluator().calculate("(1+2)"));
	}

	@Test
	public void givenExpressionOfNumberPlusNumberPlusNumber_whenParsed_thenReturnsCalculatedExpression() {
		assertEquals(6.0, new MathEvaluator().calculate("1+2+3"));
	}
	
	@Test
	public void givenExpressionOfUnaryMinusAndNumber_whenParsed_thenReturnsNegativeNumber() {
		assertEquals(-150.0, new MathEvaluator().calculate("-150"));
	}
	
	@Test
	public void givenExpressionOfOperationOnTwoUnaryMinusNumbers_whenParsed_thenReturnsCalculatedExpressionWithNegativeNumbers() {
		assertEquals(-200.0, new MathEvaluator().calculate("-150+-50"));
	}
	
	@Test
	public void givenExpressionOfPriorityOfOperation_whenParsed_thenReturnsCalculatedExpression() {
		assertEquals(30.0, new MathEvaluator().calculate("10 + 4 * 5"));
	}
	
	
	// Original tests from CodeWars
	@Test
	public void testAddition() {
		assertEquals(new MathEvaluator().calculate("1+1"), 2d, 0.01);
	}

	@Test
	public void testSubtraction() {
		assertEquals(new MathEvaluator().calculate("1 - 1"), 0d, 0.01);
	}

	@Test
	public void testMultiplication() {
		assertEquals(new MathEvaluator().calculate("1* 1"), 1d, 0.01);
	}

	@Test
	public void testDivision() {
		assertEquals(new MathEvaluator().calculate("1 /1"), 1d, 0.01);
	}

	@Test
	public void testNegative() {
		assertEquals(new MathEvaluator().calculate("-123"), -123d, 0.01);
	}

	@Test
	public void testLiteral() {
		assertEquals(new MathEvaluator().calculate("123"), 123d, 0.01);
	}

//	@Test
//	public void testExpression() {
//		assertEquals(new MathEvaluator().calculate("2 /2+3 * 4.75- -6"), 21.25, 0.01);
//	}
//
	@Test
	public void testSimple() {
		assertEquals(new MathEvaluator().calculate("12* 123"), 1476d, 0.01);
	}

//	@Test
//	public void testComplex() {
//		assertEquals(new MathEvaluator().calculate("2 / (2 + 3) * 4.33 - -6"), 7.732, 0.01);
//	}
}
