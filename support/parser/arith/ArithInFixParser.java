package parser.arith;

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
public class ArithInFixParser
    extends AbstractExpressionParser
    implements ExpressionParser<Integer> {

  public ArithInFixParser(String exp) {
    super(exp);
  }

  @Override
  protected boolean isParseable(String expr) {
    //In-fix expressions are always parsable, whether or not the expression is valid is
    // determined in the evaluator
    return true;
  }
}
