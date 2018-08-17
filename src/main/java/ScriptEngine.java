import org.apache.commons.lang3.math.NumberUtils;

public class ScriptEngine {


    private final Interpreter interpreter ;

    public ScriptEngine(){
        interpreter  = new Interpreter();
    }


    public Expression createExpression(String exp) {
        ScriptExpression expression = new ScriptExpression(interpreter);
        int position = 1;
        for (String token : exp.trim().split("\\s+")) {
            expression.getNodes().add(tokenToNode(position++, token));
        }
        return expression;
    }

    private SimpleNode tokenToNode(int position, String token) {
        SimpleNode node = null;
        if (Operator.isOp(token)) {
            Operator operator = Operator.getOp(token).orElseThrow(() -> new ExpressionException("token " + token + "is not a valid operator"));
            if (operator.isBinOp()) {
                node = new BinOpNode(position, operator);
            } else if (operator.isUnaryOp()) {
                node = new UnaryOpNode(position, operator);
            } else {
                node = new MisOpNode(position, operator);
            }

        } else if (NumberUtils.isCreatable(token)) {
            node = new LiteralNode(position, token);
        } else {

            throw new ExpressionException(String.format("Invalid token %s (position:%d)", token,position));
        }
        return node;
    }


}
