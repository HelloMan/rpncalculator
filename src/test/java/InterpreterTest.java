import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class InterpreterTest {

    @Test
    public void interpreter() {

        Interpreter interpreter = new Interpreter();

        Expression expression = new ScriptEngine().createExpression("1 2 +");

        interpreter.interpreter(expression);


    }

    @Test
    public void getDisplayValue() {

        Interpreter interpreter = new Interpreter();

        Expression expression = new ScriptEngine().createExpression("1 2 +");

        interpreter.interpreter(expression);

        String res = interpreter.getDisplayValue();
        assertThat(res.equals(3));

    }

    @Test
    public void visitBinOp() {

        Interpreter interpreter = new Interpreter();

        LiteralNode literalNode = new LiteralNode(1, "2");
        interpreter.visitLiteral(literalNode);
        literalNode = new LiteralNode(2, "3");
        interpreter.visitLiteral(literalNode);

        BinOpNode binOpNode = new BinOpNode(3, Operator.ADD);
        interpreter.visitBinOp(binOpNode);
        String res = interpreter.getDisplayValue();
        assertThat(res.equals(5));

    }

    @Test
    public void visitUnaryOp() {

        Interpreter interpreter = new Interpreter();

        LiteralNode literalNode = new LiteralNode(1, "4");
        interpreter.visitLiteral(literalNode);

        UnaryOpNode binOpNode = new UnaryOpNode(3, Operator.SQRT);
        interpreter.visitUnaryOp(binOpNode);
        String res = interpreter.getDisplayValue();
        assertThat(res.equals(2));
    }

    @Test
    public void visitUndo() {

        Interpreter interpreter = new Interpreter();

        LiteralNode literalNode = new LiteralNode(1, "4");
        interpreter.visitLiteral(literalNode);
        literalNode = new LiteralNode(2, "5");
        interpreter.visitLiteral(literalNode);

        MisOpNode binOpNode = new MisOpNode(3, Operator.UNDO);
        interpreter.visitUndo(binOpNode);
        String res = interpreter.getDisplayValue();
        assertThat(res.equals(4));
    }

    @Test
    public void visitClear() {

        Interpreter interpreter = new Interpreter();

        LiteralNode literalNode = new LiteralNode(1, "4");
        interpreter.visitLiteral(literalNode);
        literalNode = new LiteralNode(2, "5");
        interpreter.visitLiteral(literalNode);

        MisOpNode binOpNode = new MisOpNode(3, Operator.CLEAR);
        interpreter.visitUndo(binOpNode);
        String res = interpreter.getDisplayValue();
        assertThat(res.equals(""));
    }

    @Test
    public void visitLiteral() {

        Interpreter interpreter = new Interpreter();

        LiteralNode literalNode = new LiteralNode(1, "4");
        interpreter.visitLiteral(literalNode);
        literalNode = new LiteralNode(2, "5");
        interpreter.visitLiteral(literalNode);


        String res = interpreter.getDisplayValue();
        assertThat(res.equals("4 5"));
    }
}