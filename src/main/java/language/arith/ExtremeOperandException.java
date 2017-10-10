package language.arith;

/**
 * An {@code ExtremeOperandException} is thrown at runtime when the operands of an operation cause
 * the value to exceed the max value of an int
 */
public class ExtremeOperandException
    extends Exception {

  public ExtremeOperandException() {
    super();
  }

  public ExtremeOperandException(String message) {
    super(message);
  }
}
