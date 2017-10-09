package evaluator.arith;

import evaluator.IllegalPostFixExpressionException;
import evaluator.PostFixEvaluator;
import language.Operand;
import language.Operator;
import parser.arith.ArithPostFixParser;
import stack.LinkedStack;
import stack.StackInterface;

/**
 * An {@link ArithPostFixEvaluator} is a post fix evaluator over simple arithmetic expressions.
 */
public class ArithPostFixEvaluator
        implements PostFixEvaluator<Integer> {

    /**
     * Stack for use with postfix evaluator
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
        // TODO Use all of the things they've built so far to
        // create the algorithm to do post fix evaluation

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

                    stack.push(op.performOperation());
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
