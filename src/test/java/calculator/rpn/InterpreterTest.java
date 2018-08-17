package calculator.rpn;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InterpreterTest {

    @Test
    public void interpreter() {
        Expression expression = new ScriptEngine().createExpression("1 2 +");
        String result = new Interpreter().interpreter(expression);

        assertThat(result).isEqualTo("3");

    }


    @Test
    public void visitBinOp() {
        Interpreter interpreter = new Interpreter();
        interpreter.visit(new NumberLiteralToken(1, "2"));
        interpreter.visit(new NumberLiteralToken(2, "3"));
        interpreter.visit(new BinaryOperatorToken(3, Operator.ADD));

        assertThat(interpreter.getOperationStack().printStack()).isEqualTo("5");

    }

    @Test
    public void visitUnaryOp() {
        Interpreter interpreter = new Interpreter();
        interpreter.visit(new NumberLiteralToken(1, "4"));
        interpreter.visit(new FunctionToken(2, Operator.SQRT));

        assertThat(interpreter.getOperationStack().printStack()).isEqualTo("2");
    }

    @Test
    public void visitUndo() {
        Interpreter interpreter = new Interpreter();
        interpreter.visit(new NumberLiteralToken(1, "4"));
        interpreter.visit(new NumberLiteralToken(2, "5"));
        interpreter.visit(new UndoToken(3));

        assertThat(interpreter.getOperationStack().printStack()).isEqualTo("4");
    }

    @Test
    public void visitClear() {
        Interpreter interpreter = new Interpreter();
        interpreter.visit(new NumberLiteralToken(1, "4"));
        interpreter.visit(new NumberLiteralToken(2, "5"));
        interpreter.visit(new ClearToken(3));

        assertThat(interpreter.getOperationStack().printStack()).isEqualTo("");
    }

    @Test
    public void visitLiteral() {
        Interpreter interpreter = new Interpreter();
        interpreter.visit(new NumberLiteralToken(1, "2"));
        interpreter.visit(new NumberLiteralToken(2, "3"));

        assertThat(interpreter.getOperationStack().printStack()).isEqualTo("2 3");
    }
}