package calculator.rpn;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;



public class UndoManager {

    @Getter(AccessLevel.PACKAGE)
    private final Deque<Deque<NumberLiteralToken>> tokens = new ArrayDeque<>();
    @Getter(AccessLevel.PACKAGE)
    private final TokenStack tokenStack;

    public UndoManager(TokenStack tokenStack) {
        this.tokenStack = tokenStack;
    }


    public void apply(NumberLiteralToken... literalTokens) {
        Deque<NumberLiteralToken> undoTokens = new ArrayDeque<>();
        Arrays.stream(literalTokens).forEach(undoTokens::push);

        tokens.push(undoTokens);
    }

    public void applyEmpty() {
        tokens.push(new ArrayDeque<>());
    }

    public void apply(Collection<NumberLiteralToken> literalTokens) {
        Deque<NumberLiteralToken> undoTokens = new ArrayDeque<>();
        literalTokens.forEach(undoTokens::push);
        tokens.push(undoTokens);
    }

    public void undo() {

        if (tokenStack.peek() != null) {
            tokenStack.pop().isCalculated();
        }
        if (tokens.peek() != null) {
            Deque<NumberLiteralToken> undoTokens = this.tokens.pop();
            undoTokens.forEach(n -> tokenStack.push(n));
        }
    }
}
