package parser.arith;

import parser.ExpressionParser;

public class ArithInFixParser
    extends AbstractArithExpressionParser
    implements ExpressionParser<Integer> {

  public ArithInFixParser(String exp) {
    super(exp);
  }

  @Override
  protected boolean isParseable(String expr) {
    //In-fix expressions are always parsable, whether or not the expression is valid is
    // determined in the evaluator
    return true;
  }
}
