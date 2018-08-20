package calculator.rpn;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface OperationStack {

    /**
     * push token to the stack
     * @param token
     * @param calculated - token is calculated or not
     */
    void pushToken(NumberLiteralToken token,boolean calculated);

    /**
     * pop  number tokens from stack for the binary operator token
     * @param token
     * @return a pair of  number tokens
     */
    Optional<Pair<NumberLiteralToken>> popTokens(BinaryOperatorToken token);

    /**
     * pop number Tokens from stack for the function token
     * @param token
     * @return a collection of number token
     */
    List<NumberLiteralToken> popTokens(FunctionToken token);

    /**
     * print the numbers in the stack
     * @return
     */
    String printStack();

    /**
     * clear tokens in the stack
     */
    void clear();

    /**
     * undo an operation
     */
    void undo();


}
