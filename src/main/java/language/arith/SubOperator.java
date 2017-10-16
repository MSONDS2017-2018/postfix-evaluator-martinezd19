package language.arith;

import language.BinaryOperator;
import language.Operand;

/**
 * The {@code SubOperator} is an operator that performs subtraction on two
 * integers.
 */
public class SubOperator
    extends BinaryOperator<Integer> {

  @Override
  public int getPrecendence() {
    return 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Operand<Integer> performOperation()
      throws ExtremeOperandException {
    Operand<Integer> op0 = this.getOp0();
    Operand<Integer> op1 = this.getOp1();
    if (op0 == null || op1 == null) {
      throw new IllegalStateException(
          "Could not perform operation prior to operands being set.");
    }
    Integer result = op0.getValue() - op1.getValue();

    if (result == Integer.MAX_VALUE) {
      throw new ExtremeOperandException();
    }

    return new Operand<Integer>(result);
  }
}
