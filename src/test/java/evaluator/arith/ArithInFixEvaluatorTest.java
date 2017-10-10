package evaluator.arith;

import evaluator.Expression;
import evaluator.IllegalPostFixExpressionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArithInFixEvaluatorTest {

  private Expression<Integer> evaluator;

  @Before
  public void setup() {
    evaluator = new ArithInFixEvaluator();
  }

  @Test(timeout = 5000)
  public void testEvaluateSimple() {
    Integer result = evaluator.evaluate("1");
    assertEquals(new Integer(1), result);
  }

  @Test(timeout = 5000)
  public void testEvaluatePlus() {
    Integer result = evaluator.evaluate("1 + 2");
    assertEquals(Integer.valueOf(3), result);

    result = evaluator.evaluate("1 + 2 + 3");
    assertEquals(Integer.valueOf(6), result);

    result = evaluator.evaluate("10000 + 1000 + 100 + 10 + 1");
    assertEquals(Integer.valueOf(11111), result);
  }

  @Test(timeout = 5000)
  public void testEvaluateSub() {
    Integer result = evaluator.evaluate("1 - 2");
    assertEquals(Integer.valueOf(-1), result);

    result = evaluator.evaluate("1 - 2 - 3");
    assertEquals(Integer.valueOf(2), result);

    result = evaluator.evaluate("1000 - 100 - 10 - 1");
    assertEquals(Integer.valueOf(909), result);
  }

  @Test(timeout = 5000)
  public void testEvaluateMult() {
    Integer result = evaluator.evaluate("1 * 2");
    assertEquals(Integer.valueOf(2), result);

    result = evaluator.evaluate("1 * 2 * 3");
    assertEquals(Integer.valueOf(6), result);

    result = evaluator.evaluate("1 * 2 * 3 * 4");
    assertEquals(Integer.valueOf(24), result);
  }

  @Test(timeout = 5000)
  public void testEvaluateDiv() {
    Integer result = evaluator.evaluate("1 / 2");
    assertEquals(Integer.valueOf(0), result);

    result = evaluator.evaluate("10 / 5");
    assertEquals(Integer.valueOf(2), result);

    result = evaluator.evaluate("15 / 5 / 3");
    assertEquals(Integer.valueOf(15), result);
  }

  @Test(timeout = 5000)
  public void testEvaluateExp() {
    Integer result = evaluator.evaluate("1 ^ 2");
    assertEquals(Integer.valueOf(1), result);

    result = evaluator.evaluate("10 ^ 2");
    assertEquals(Integer.valueOf(100), result);

    result = evaluator.evaluate("2 ^ 2 ^ 2");
    assertEquals(Integer.valueOf(16), result);
  }

  @Test(timeout = 5000)
  public void testOrderOfOperations() {
    Integer result = evaluator.evaluate("1 - 3 * 5");
    assertEquals(Integer.valueOf(-14), result);

    result = evaluator.evaluate("1 * 3 - 5");
    assertEquals(Integer.valueOf(-2), result);

    result = evaluator.evaluate("1 - 3 / 3 * 5");
    assertEquals(Integer.valueOf(1), result);

    result = evaluator.evaluate("1 ^ 3 * 5 ^ 2 - 8 + 4");
    assertEquals(Integer.valueOf(13), result);
  }

  @Test(timeout = 5000, expected = IllegalPostFixExpressionException.class)
  public void testInvalidExpression() {
    evaluator.evaluate("1 2");
  }
}
