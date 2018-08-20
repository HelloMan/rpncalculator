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

        if (Operator.isOperator(token)) {
            Operator operator = Operator.getOperator(token).orElseThrow(() -> new ExpressionException("token " + token + "is not a valid operator"));
            switch (operator) {
                case SQRT:
                    return new FunctionToken(position, operator);
                case ADD:
                case SUB:
                case MUL:
                case DIV:
                    return  new BinaryOperatorToken(position, operator);
                case UNDO:
                    return new UndoToken(position);
                case CLEAR:
                    return new ClearToken(position);

            }

        } else if (NumberUtils.isCreatable(token)) {
            return new NumberLiteralToken(position, token);
        }

        throw new ExpressionException(String.format("Invalid token %s (position:%d)", token, position));

    }


}
