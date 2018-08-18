package calculator.rpn;

import java.util.Scanner;

public class RPNCalculator {


    public static void main(String[] args) {

        ScriptEngine scriptEngine = new ScriptEngine();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the expression: ");
        while (scan.hasNextLine()) {
            String exp = scan.nextLine();
            if (exp != null && exp.trim().length() > 0) {
                Expression expression = scriptEngine.createExpression(exp);
                System.out.println("Stack:" + expression.evaluate());
            }
            System.out.println("Please enter the expression: ");

        }
    }
}
