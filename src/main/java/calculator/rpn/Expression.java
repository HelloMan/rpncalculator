package calculator.rpn;

import java.util.List;

public interface Expression {

    /**
     * @return number on the stack
     */
    String evaluate();

    /**
     *
     * @return a collection of  tokens
     */
    List<Token> getTokens();
}
