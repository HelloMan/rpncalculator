import java.util.Scanner;

public class RPNCalculator {


    public static void main(String[] args) {

        ScriptEngine scriptEngine = new ScriptEngine();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the expression: ");
        while (scan.hasNextLine()) {
            Expression expression = scriptEngine.createExpression(scan.nextLine());
            System.out.println("Stack:" + expression.getDisplayValue());
            System.out.println("Please enter the expression: ");

        }
    }
}
