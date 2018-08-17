package calculator.rpn;

import java.util.Collection;
import java.util.Iterator;

public interface TokenStack {

    void push(NumberLiteralToken token);

    NumberLiteralToken pop();

    String printStack();

    Iterator iterator();

    void clear();

    NumberLiteralToken peek();

    Collection<NumberLiteralToken> getTokens();


}
