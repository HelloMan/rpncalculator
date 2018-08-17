package calculator.rpn;

public class UnaryOperatorToken extends OperatorToken {


    public UnaryOperatorToken(int id, Operator op) {
        super(id,op);
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
