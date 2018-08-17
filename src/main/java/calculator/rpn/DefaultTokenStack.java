package calculator.rpn;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.stream.Collectors;

public class DefaultTokenStack implements TokenStack {

    private final Deque<NumberLiteralToken> tokens = new ArrayDeque<>();


    @Override
    public void push(NumberLiteralToken token) {
        tokens.push(token);
    }

    @Override
    public NumberLiteralToken pop() {
        return tokens.pop();
    }



    @Override
    public String printStack() {

        Deque<NumberLiteralToken> printTokens = new ArrayDeque<>();

        tokens.forEach(n -> printTokens.push(n));

        return printTokens.stream()
                .map(NumberLiteralToken::formatText)
                .collect(Collectors.joining(" "));
    }

    @Override
    public Iterator iterator() {
        return tokens.iterator();
    }

    @Override
    public void clear() {
        tokens.clear();
    }

    @Override
    public NumberLiteralToken peek() {
        return tokens.peek();
    }

    @Override
    public Collection<NumberLiteralToken> getTokens() {
        return tokens;
    }
}
