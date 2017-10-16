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
 * An {@link ArithInFixEvaluator} is a in-fix evaluator that takes +, -, *, /, and ! operators, as
 * well as parenthesis.
 */
public class ArithInFixEvaluator
    implements Expression<Integer> {

  /**
   * Stacks for use with infix evaluator.
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
   * Evaluates a postfix expression including parenthesis. Each operation is truncated to an
   * integer, so beware of division that produces a fractional answer (ex. "10 * (4 / 8)" evaluates
   * to <b>0</b>, not <b>5</b>
   *
   * @param exp The string expression to be evaluated. Operators must have spaces around them,
   *     only integer numbers are accepted, and multiplication is not implied (ex. "5*7" is
   *     <b>NOT</b> valid. "5 (8 / 8)" is <b>NOT</b> valid. "20 / (2 * 2)" <b>IS</b> valid.
   *
   * @return the result
   */
  @Override
  public final Integer evaluate(final String exp) {
    return parseParen(exp);
  }

  /**
   * Evaluates a postfix expression.
   *
   * @return the result
   */
  private final Integer evalString(final String expr) {

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

  /**
   * Fully parses an in-fix expression containing parentheses.
   *
   * @param exp expression to be evaluated
   *
   * @return Integer representing answer
   */
  private final Integer parseParen(String exp) {
    char[]  cArray         = exp.toCharArray();
    int     counter        = 0;
    int     startIndex     = 0;
    boolean openParenFound = false;

    // Loop through string and find top-level pair of parentheses
    for (int i = 0; i < exp.length(); i++) {
      switch (cArray[i]) {
        case '(':
          if (!openParenFound) {
            openParenFound = true;
            startIndex = i;
          }
          counter++;
          break;
        case ')':
          if (openParenFound) {
            if (--counter == 0) {
              // Upon finding a top-level parenthetical expression, recurse and replace
              // parenthetical with evaluated expression
              String toSolve = exp.substring(startIndex + 1, i);
              exp = new StringBuffer(exp).replace(startIndex,
                  i + 1,
                  parseParen(toSolve).toString())
                                         .toString();
            }
          } else {
            throw new IllegalPostFixExpressionException(
                "Missing opening parenthesis");
          }
          break;
      }
    }

    if (counter != 0) {
      throw new IllegalPostFixExpressionException("Missing closing parenthesis");
    }

    return evalString(exp);
  }

}
