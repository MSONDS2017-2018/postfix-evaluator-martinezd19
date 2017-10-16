package evaluator.arith;

import evaluator.Expression;
import evaluator.IllegalPostFixExpressionException;
import language.Operand;
import language.Operator;
import language.arith.ExtremeOperandException;
import parser.arith.ArithPostFixParser;
import stack.LinkedStack;
import stack.StackInterface;

/**
 * An {@link ArithPostFixEvaluator} is a post fix evaluator over simple arithmetic expressions.
 */
public class ArithPostFixEvaluator
    implements Expression<Integer> {

  /**
   * Stack for use with postfix evaluator.
   */
  private final StackInterface<Operand<Integer>> stack;

  /**
   * Constructs an {@link ArithPostFixEvaluator}.
   */
  public ArithPostFixEvaluator() {
    this.stack = new LinkedStack<>();
  }

  /**
   * Evaluates a postfix expression.
   *
   * @return the result
   */
  @Override
  public final Integer evaluate(final String expr) {

    ArithPostFixParser parser = new ArithPostFixParser(expr);
    while (parser.hasNext()) {
      switch (parser.nextType()) {
        case OPERAND:
          stack.push(parser.nextOperand());
          break;
        case OPERATOR:
          Operator op = parser.nextOperator();

          for (int i = op.getNumberOfArguments() - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
              throw new IllegalPostFixExpressionException(
                  "Missing operand");
            }

            op.setOperand(i, stack.pop());
          }

          try {
            stack.push(op.performOperation());
          } catch (ExtremeOperandException e) {
            System.out.println(
                "Operands too large; result of operation totaled over Integer.MAX_VALUE");
            return null;
          }

          break;
        default:
          throw new IllegalStateException(
              "parser.nextType() should only return OPERAND or OPERATOR");
      }
    }

    Integer returnValue = stack.pop()
                               .getValue();

    if (stack.size() != 0) {
      throw new IllegalPostFixExpressionException("Missing operator");
    }

    return returnValue;
  }

}
