package evaluator.arith;

import evaluator.Expression;
import evaluator.IllegalPostFixExpressionException;
import language.Operand;
import language.Operator;
import language.arith.ExtremeOperandException;
import parser.arith.ArithInFixParser;
import stack.LinkedStack;
import stack.StackInterface;

/**
 * An {@link ArithPostFixEvaluator} is a in fix evaluator over simple arithmetic expressions.
 */
public class ArithInFixEvaluator
    implements Expression<Integer> {

  /**
   * Stack for use with infix evaluator
   */
  private final StackInterface<Operand<Integer>>  oprStack;
  private final StackInterface<Operator<Integer>> optStack;

  /**
   * Constructs an {@link ArithInFixEvaluator}.
   */
  public ArithInFixEvaluator() {
    this.oprStack = new LinkedStack<>();
    this.optStack = new LinkedStack<>();
  }

  /**
   * Evaluates a postfix expression.
   *
   * @return the result
   */
  @Override
  public final Integer evaluate(final String expr) {

    ArithInFixParser parser = new ArithInFixParser(expr);
    while (parser.hasNext()) {
      switch (parser.nextType()) {
        case OPERAND:
          oprStack.push(parser.nextOperand());
          break;
        case OPERATOR:
          Operator op = parser.nextOperator();

          while (!optStack.isEmpty() && op.getPrecendence() < optStack.top()
                                                                      .getPrecendence()) {
            Operator tempOp = optStack.pop();
            for (int i = tempOp.getNumberOfArguments() - 1; i >= 0; i--) {
              if (oprStack.isEmpty()) {
                throw new IllegalPostFixExpressionException("Missing operand");
              }

              tempOp.setOperand(i, oprStack.pop());
            }

            try {
              oprStack.push(tempOp.performOperation());
            } catch (ExtremeOperandException e) {
              System.out.println(
                  "Operands too large; result of operation totaled over Integer.MAX_VALUE");
              return null;
            }
          }

          optStack.push(op);
          break;
        default:
          throw new IllegalStateException(
              "parser.nextType() should only return OPERAND or OPERATOR");
      }
    }

    while (!optStack.isEmpty()) {
      Operator tempOp = optStack.pop();
      for (int i = tempOp.getNumberOfArguments() - 1; i >= 0; i--) {
        if (oprStack.isEmpty()) {
          throw new IllegalPostFixExpressionException("Missing operand");
        }

        tempOp.setOperand(i, oprStack.pop());
      }

      try {
        oprStack.push(tempOp.performOperation());
      } catch (ExtremeOperandException e) {
        System.out.println(
            "Operands too large; result of operation totaled over Integer.MAX_VALUE");
        return null;
      }
    }

    Integer returnValue = oprStack.pop()
                                  .getValue();

    if (oprStack.size() != 0) {
      throw new IllegalPostFixExpressionException("Missing operator");
    }

    if (optStack.size() != 0) {
      throw new IllegalPostFixExpressionException("Missing operand");
    }

    return returnValue;
  }

}
