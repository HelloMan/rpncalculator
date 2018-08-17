package calculator.rpn;

import lombok.Getter;

public abstract class OperatorToken extends Token {
    @Getter
    private final Operator operator;

    protected OperatorToken(int position, Operator operator) {
        super(position);
        this.operator = operator;
    }
}
