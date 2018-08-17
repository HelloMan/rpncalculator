package calculator.rpn;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InterpreterTest {

    @Test
    public void interpreter() {

        Interpreter interpreter = new Interpreter();

        Expression expression = new ScriptEngine().createExpression("1 2 +");


        String res =  interpreter.interpreter(expression);

        assertThat(res).isEqualTo("3");


    }



    @Test
    public void visitBinOp() {

        Interpreter interpreter = new Interpreter();

        NumberLiteralToken numberLiteralToken = new NumberLiteralToken(1, "2");
        interpreter.visit(numberLiteralToken);
        numberLiteralToken = new NumberLiteralToken(2, "3");
        interpreter.visit(numberLiteralToken);

        BinaryOperatorToken binaryOperatorToken = new BinaryOperatorToken(3, Operator.ADD);
        interpreter.visit(binaryOperatorToken);


        assertThat(interpreter.getTokenStack().printStack()).isEqualTo("5");

    }

    @Test
    public void visitUnaryOp() {

        Interpreter interpreter = new Interpreter();

        NumberLiteralToken numberLiteralToken = new NumberLiteralToken(1, "4");
        interpreter.visit(numberLiteralToken);

        UnaryOperatorToken token = new UnaryOperatorToken(3, Operator.SQRT);
        interpreter.visit(token);
        assertThat(interpreter.getTokenStack().printStack()).isEqualTo("2");
    }

    @Test
    public void visitUndo() {

        Interpreter interpreter = new Interpreter();

        NumberLiteralToken numberLiteralToken = new NumberLiteralToken(1, "4");
        interpreter.visit(numberLiteralToken);
        numberLiteralToken = new NumberLiteralToken(2, "5");
        interpreter.visit(numberLiteralToken);

        UndoToken undoToken = new UndoToken(3);
        interpreter.visit(undoToken);
        assertThat(interpreter.getTokenStack().printStack()).isEqualTo("4");
    }

    @Test
    public void visitClear() {

        Interpreter interpreter = new Interpreter();

        NumberLiteralToken numberLiteralToken = new NumberLiteralToken(1, "4");
        interpreter.visit(numberLiteralToken);
        numberLiteralToken = new NumberLiteralToken(2, "5");
        interpreter.visit(numberLiteralToken);

        ClearToken token = new ClearToken(3 );
        interpreter.visit(token);

        assertThat(interpreter.getTokenStack().getTokens().size()).isEqualTo(0);
    }

    @Test
    public void visitLiteral() {

        Interpreter interpreter = new Interpreter();

        NumberLiteralToken numberLiteralToken = new NumberLiteralToken(1, "4");
        interpreter.visit(numberLiteralToken);
        numberLiteralToken = new NumberLiteralToken(2, "5");
        interpreter.visit(numberLiteralToken);

        assertThat(interpreter.getTokenStack().getTokens().size()).isEqualTo(2);
    }
}