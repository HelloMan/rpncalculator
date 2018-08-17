package calculator.rpn;


import lombok.Getter;

public abstract class Token {

    @Getter
    private final int position;

    protected Token(int position) {
        this.position = position;
    }

    abstract void accept(TokenVisitor visitor);
}
