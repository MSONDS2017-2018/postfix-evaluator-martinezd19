package parser.arith;

import java.util.Scanner;

import parser.ExpressionParser;

/**
 * <p> An {@code ArithPostFixParser} is a post fix parser for arithmetic expressions. </p> <p> You
 * shouldn't worry about how this class is implemented but you should be aware of its usage. </p>
 * <p>
 * <pre>
 * Example:
 *   ExpressionParser<Integer> parser = new ArithPostFixParser("1 2 +");
 *  boolean next = parser.hasNext(); // Returns true because there are more
 * elements to be consumed
 *  Type nextP = parser.nextType(); // Returns OPERAND because the next element
 * is an operand
 *  Operand<Integer> o0 = parser.nextOperand(); // Returns an operand wrapping
 * 1
 *  next = parser.hasNext(); // Returns true because there are more elements to
 * be consumed
 *  nextP = parser.nextType(); // Returns OPERAND because the next element is
 * an
 * operand
 *  Operand<Integer> o1 = parser.nextOperand(); // Returns an operand wrapping
 * 2
 *  next = parser.hasNext(); // Returns true because there are more elements to
 * be consumed
 *  nextP = parser.nextType(); // Returns OPERATOR because the next element is
 * an operand
 *  Operator<Integer> operator = parser.nextOperator(); // Returns the
 * PlusOperator
 *  next = parser.hasNext(); // Returns false because all of the elements have
 * been consumed
 * </pre>
 *
 * @author jcollard, jddevaug
 */
public class ArithPostFixParser
    extends AbstractArithExpressionParser
    implements ExpressionParser<Integer> {

  public ArithPostFixParser(String exp) {
    super(exp.trim());
  }

  @Override
  protected boolean isParseable(String expr) {
    Scanner s = new Scanner(expr);
    while (s.hasNext()) {
      // If we find an integer, we are good.
      if (s.hasNextInt()) {
        s.nextInt();
        continue;
      }
      String token = s.next();
      // If we find a string that is not an operator
      // return false
      if (!OPERATORS.containsKey(token)) {
        s.close();
        return false;
      }
    }
    s.close();
    // If we make it to the end of the expression we are good
    return true;
  }
}
