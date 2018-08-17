package calculator.rpn;

import lombok.Data;

@Data
public class BinaryOperatorToken extends OperatorToken {

    public BinaryOperatorToken(int id, Operator operator) {
        super(id, operator);

    }


    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }


}
