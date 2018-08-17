package calculator.rpn;

import org.junit.Test;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
public class DefaultOperationStackTest {

    @Test
    public void pushToken() {

        DefaultOperationStack res = new DefaultOperationStack();
        res.pushToken(new NumberLiteralToken(1, "3"),false);
        assertThat(res.getTokens().size()).isEqualTo(1);
        assertThat(res.getUndoActions().size()).isEqualTo(1);

        res.pushToken(new NumberLiteralToken(1, "3"),true);
        assertThat(res.getTokens().size()).isEqualTo(2);
        assertThat(res.getUndoActions().size()).isEqualTo(1);
    }

    @Test
    public void popTokensForBinaryOperatorToken() {

        DefaultOperationStack res = new DefaultOperationStack();
        res.pushToken(new NumberLiteralToken(1, "3"),false);
        res.pushToken(new NumberLiteralToken(1, "4"),false);

        assertThat(res.getTokens().size()).isEqualTo(2);
        assertThat(res.getUndoActions().size()).isEqualTo(2);

        Optional<Pair<NumberLiteralToken>> tokenPair = res.popTokens(new BinaryOperatorToken(3, Operator.ADD));
        assertThat(tokenPair.isPresent()).isTrue();
        assertThat(tokenPair.get().getFirst().getNumber()).isEqualTo(3l);
        assertThat(tokenPair.get().getSecond().getNumber()).isEqualTo(4l);
        assertThat(res.getTokens().isEmpty()).isTrue();

        assertThat(res.getUndoActions().size()).isEqualTo(3);

    }


    @Test
    public void popTokensForFunctionToken() {
        DefaultOperationStack res = new DefaultOperationStack();
        res.pushToken(new NumberLiteralToken(1, "3"),false);

        assertThat(res.getTokens().size()).isEqualTo(1);
        assertThat(res.getUndoActions().size()).isEqualTo(1);

        Collection<NumberLiteralToken> tokens = res.popTokens(new FunctionToken(3, Operator.SQRT));
        assertThat(tokens.size()).isEqualTo(1);
        assertThat(res.getTokens().isEmpty()).isTrue();
        assertThat(res.getUndoActions().size()).isEqualTo(2);

    }

    @Test
    public void printStack() {

        DefaultOperationStack res = new DefaultOperationStack();
        res.pushToken(new NumberLiteralToken(1, "3"),false);


        assertThat(res.printStack()).isEqualTo("3");
    }


    @Test
    public void clear() {


        DefaultOperationStack res = new DefaultOperationStack();
        res.pushToken(new NumberLiteralToken(1, "3"),false);

        res.clear();
        assertThat(res.getTokens()).isEmpty();
        assertThat(res.getUndoActions().size()).isEqualTo(2);
    }

    @Test
    public void undo() {

        DefaultOperationStack res = new DefaultOperationStack();
        res.pushToken(new NumberLiteralToken(1, "3"),false);


        res.undo();
        assertThat(res.getTokens()).isEmpty();
    }

}