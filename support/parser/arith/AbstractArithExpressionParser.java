package parser.arith;

import language.Operand;
import language.Operator;
import language.arith.DivOperator;
import language.arith.ExponentOperator;
import language.arith.MultOperator;
import language.arith.NegateOperator;
import language.arith.PlusOperator;
import language.arith.SubOperator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import parser.ExpressionParser;

/**
 * Abstract arithmetic expression parser. Subclasses must implement the {@code isParsable} method.
 */
public abstract class AbstractArithExpressionParser
    implements ExpressionParser<Integer> {

  protected static final Map<String, OperatorConstructor> OPERATORS;

  static {
    OPERATORS =
        new HashMap<>();

    OPERATORS.put("+", new OperatorConstructor() {

      @Override
      public Operator<Integer> construct() {
        return new PlusOperator();
      }
    });

    OPERATORS.put("*", new OperatorConstructor() {

      @Override
      public Operator<Integer> construct() {
        return new MultOperator();
      }
    });

    OPERATORS.put("-", new OperatorConstructor() {

      @Override
      public Operator<Integer> construct() {
        return new SubOperator();
      }
    });

    OPERATORS.put("/", new OperatorConstructor() {

      @Override
      public Operator<Integer> construct() {
        return new DivOperator();
      }
    });

    OPERATORS.put("^", new OperatorConstructor() {

      @Override
      public Operator<Integer> construct() {
        return new ExponentOperator();
      }
    });

    OPERATORS.put("!", new OperatorConstructor() {

      @Override
      public Operator<Integer> construct() {
        return new NegateOperator();
      }
    });
  }

  private String            expr;
  private Scanner           tokenizer;
  private Operand<Integer>  nextOperand;
  private Operator<Integer> nextOperator;

  /**
   * Creates an {@link ArithPostFixParser} over {@code expr}.
   *
   * @param expr the arithmetic expression to parse
   *
   * @throws NullPointerException if expr is null
   * @throws IllegalArgumentException if expr is no a valid arithmetic expression.
   */
  public AbstractArithExpressionParser(String expr) {
    if (expr == null) {
      throw new NullPointerException("The expression must be non-null.");
    }
    if (!isParseable(expr)) {
      throw new IllegalArgumentException("The string \"" + expr
          + "\" is not a valid ArithPostFix expression.");
    }
    this.expr = expr;
    this.tokenizer = new Scanner(this.expr);
  }

  protected abstract boolean isParseable(String expr);

  /**
   * {@inheritDoc}.
   */
  @Override
  public boolean hasNext() {
    getNextParsable();
    return nextOperand != null || nextOperator != null;
  }

  private final void getNextParsable() {
    // If the next parseable has not been given, do nothing.
    if (nextOperand != null || nextOperator != null) {
      return;
    }
    // If the token is an int, generate an integer operand
    if (tokenizer.hasNextInt()) {
      int token = tokenizer.nextInt();
      nextOperand = new Operand<Integer>(token);
      return;
    } else if (tokenizer.hasNext()) {
      // Otherwise return the associated operator
      String token = tokenizer.next();
      nextOperator = OPERATORS.get(token)
                              .construct();
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Type nextType() {
    if (!hasNext()) {
      throw new IllegalStateException("End of expression was reached.");
    }
    if (nextOperator != null) {
      return Type.OPERATOR;
    }
    return Type.OPERAND;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Operand<Integer> nextOperand() {
    if (nextType() != ExpressionParser.Type.OPERAND) {
      throw new IllegalStateException("Operand could not be parsed.");
    }
    Operand<Integer> temp = nextOperand;
    nextOperand = null;
    return temp;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Operator<Integer> nextOperator() {
    if (nextType() != ExpressionParser.Type.OPERATOR) {
      throw new IllegalStateException("Operator could not be parsed.");
    }
    Operator<Integer> temp = nextOperator;
    nextOperator = null;
    return temp;
  }

  private static interface OperatorConstructor {

    public Operator<Integer> construct();
  }

}
