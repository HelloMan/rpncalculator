package calculator.rpn;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
public class ScriptExpressionTest {

    @Test
    public void evaluate() {

        ScriptExpression scriptExpression = new ScriptExpression(new Interpreter());
        scriptExpression.getTokens().add(new NumberLiteralToken(1, "3"));
        scriptExpression.getTokens().add(new NumberLiteralToken(2, "3"));
        scriptExpression.getTokens().add(new BinaryOperatorToken(3, Operator.MUL));
        String res =  scriptExpression.evaluate();
        assertThat(res).isEqualTo("9");
    }
}