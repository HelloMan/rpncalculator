package calculator.rpn;

import org.apache.commons.lang3.math.NumberUtils;

public class ScriptEngine {


    private final Interpreter interpreter;

    public ScriptEngine() {
        interpreter = new Interpreter();
    }


    public Expression createExpression(String expression) {
        ScriptExpression scriptExpression = new ScriptExpression(interpreter);
        int position = 1;
        for (String token : expression.trim().split("\\s+")) {
            scriptExpression.getTokens().add(parseToken(position++, token));
        }
        return scriptExpression;
    }

    private Token parseToken(int position, String token) {
        Token node = null;
        if (Operator.isOperator(token)) {
            Operator operator = Operator.getOperator(token).orElseThrow(() -> new ExpressionException("token " + token + "is not a valid operator"));
            switch (operator) {
                case SQRT:
                    node = new UnaryOperatorToken(position, operator);
                    break;
                case ADD:
                case SUB:
                case MUL:
                case DIV:
                    node = new BinaryOperatorToken(position, operator);
                    break;
                case UNDO:
                    node = new UndoToken(position);
                    break;
                case CLEAR:
                    node = new ClearToken(position);
                    break;
                default:
                    break;

            }

        } else if (NumberUtils.isCreatable(token)) {
            node = new NumberLiteralToken(position, token);
        } else {

            throw new ExpressionException(String.format("Invalid token %s (position:%d)", token, position));
        }
        return node;
    }


}
