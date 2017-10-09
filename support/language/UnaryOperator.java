package language;

/**
 * A {@link UnaryOperator} is an {@link Operator} that performs an operation on a single argument.
 *
 * @param <T> the type of the {@link Operand} being evaluated
 */
public abstract class UnaryOperator<T>
        implements Operator<T> {

    private Operand<T> op;

    /**
     * Returns the number of arguments.
     */
    @Override
    public final int getNumberOfArguments() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    public void setOperand(int i, Operand<T> operand) {
        if (operand == null) {
            throw new NullPointerException("Could not set null operand.");
        }

        if (i != 0) {
            throw new IllegalArgumentException(
                    "Unary operator only accepts operand 0 "
                            + "but recieved " + i + ".");
        }

        if (op != null) {
            throw new IllegalStateException(
                    "Unary operand has been previously set.");
        }
        op = operand;
    }

    /**
     * Returns the operand.
     *
     * @return the operand
     */
    public Operand<T> getOp() {
        return op;
    }
}
