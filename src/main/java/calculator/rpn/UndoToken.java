package calculator.rpn;

public class UndoToken extends OperatorToken {

    public UndoToken(int id) {
        super(id, Operator.UNDO);

    }


    @Override
    void accept(TokenVisitor visitor) {
        visitor.visit(this);

    }
}
