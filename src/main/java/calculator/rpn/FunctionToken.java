package calculator.rpn;

public class FunctionToken extends OperatorToken {


    public FunctionToken(int id, Operator op) {
        super(id,op);
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
