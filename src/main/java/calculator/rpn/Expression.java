package calculator.rpn;

import java.util.List;

public interface Expression {

    /**
     * @return original
     */
    String evaluate();

    /**
     *
     * @return a collection of  tokens
     */
    List<Token> getTokens();
}
