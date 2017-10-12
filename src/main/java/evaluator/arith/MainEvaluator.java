package evaluator.arith;

import evaluator.IllegalPostFixExpressionException;

import java.util.Scanner;

public class MainEvaluator {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    while(true) {
      System.out.println("Enter P for postfix or I for infix, E to exit:");
      String line = in.nextLine();
      switch (line.toLowerCase()) {
        case "p":
          ArithPostFixEvaluator evalPostfix = new ArithPostFixEvaluator();
          try {
            System.out.println(evalPostfix.evaluate(getExp()));
          } catch (IllegalPostFixExpressionException e) {
            System.out.println("Invalid expression");
          }
          break;
        case "i":
          ArithInFixEvaluator evalInfix = new ArithInFixEvaluator();
          try {
            System.out.println(evalInfix.evaluate(getExp()));
          } catch (IllegalPostFixExpressionException e) {
            System.out.println("Invalid expression");
          }
          break;
        case "e":
          System.exit(0);
          break;
        default:
          System.out.println("Invalid input");
          break;
      }
    }
  }

  private static final String getExp() {
    Scanner in = new Scanner(System.in);

    System.out.println("Enter expression");
    String line = in.nextLine();
    return line;
  }
}
