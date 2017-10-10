package language.arith;

import language.Operand;
import language.UnaryOperator;

/**
 * The {@code NegateOperator} is an operator that performs negation on a single integer
 *
 * @author jcollard, jddevaug
 */
public class NegateOperator
    extends UnaryOperator<Integer> {

  @Override
  public int getPrecendence() {
    return 3;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public final Operand<Integer> performOperation() {
    Operand<Integer> op = this.getOp();
    if (op == null) {
      throw new IllegalStateException(
          "Could not perform operation prior to operand being set.");
    }
    Integer result = -1 * op.getValue();
    return new Operand<Integer>(result);
  }
}
