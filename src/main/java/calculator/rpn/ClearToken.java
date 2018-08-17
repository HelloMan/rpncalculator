package calculator.rpn;

public class ClearToken extends OperatorToken {

    public ClearToken(int id) {
        super(id, Operator.CLEAR);

    }


    @Override
    void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
