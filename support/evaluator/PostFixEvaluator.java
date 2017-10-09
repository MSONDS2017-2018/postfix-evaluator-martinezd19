package evaluator;

/**
 * A {@code PostFixEvaluator} evaluates post fix expressions.
 *
 * @param <T> the type of result to be evaluated
 *
 * @author jcollard, jddevaug
 */
public interface PostFixEvaluator<T> {

    /**
     * Evaluates the post fix expression and returns a value.
     *
     * @param expr the expression to be evaluate
     *
     * @return the value of evaluating expr
     *
     * @throws IllegalPostFixExpressionException if the expression is not a valid post fix
     *         expression
     */
    public T evaluate(String expr);

}
