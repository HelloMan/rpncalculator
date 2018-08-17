package calculator.rpn;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Token interpreter ,parse token and operate numbers on token stack
 */
@Slf4j
public class Interpreter implements TokenVisitor {

    @Getter
    private final OperationStack operationStack;


    public Interpreter() {
        operationStack = new DefaultOperationStack();
    }

    public String interpreter(Expression expression) {
        for (Token node : expression.getTokens()) {
            try {
                node.accept(this);
            } catch (ExpressionException e) {
                log.warn(e.getMessage());
                break;
            }
        }
        return operationStack.printStack();

    }


    @Override
    public void visit(BinaryOperatorToken token) {

        Pair<NumberLiteralToken> tokenPair = operationStack.popTokens(token).orElseThrow(
                () -> new ExpressionException(String.format("Operator %s (position:%d): insufficient parameters",
                        token.getOperator().getImage(), token.getPosition())));

        Arithmetic arithmetic = new Arithmetic();
        Number value = arithmetic.calculate(tokenPair.getFirst().getNumber(), tokenPair.getSecond().getNumber(), token.getOperator());
        operationStack.pushToken(new NumberLiteralToken(-1, value), true);
    }

    @Override
    public void visit(FunctionToken token) {
        if (token.getOperator() != Operator.SQRT) {
            return;
        }
        List<NumberLiteralToken> tokens = operationStack.popTokens(token);
        if (tokens.isEmpty()) {

            throw new ExpressionException(String.format("Operator %s (position:%d): insufficient parameters",
                    token.getOperator().getImage(), token.getPosition()));
        }

        Number value = new Arithmetic().sqrt(tokens.get(0).getNumber());
        //pushToken back the literal to stack
        operationStack.pushToken(new NumberLiteralToken(-1, value), true);

    }

    @Override
    public void visit(UndoToken token) {
        operationStack.undo();
    }

    @Override
    public void visit(ClearToken token) {
        operationStack.clear();
    }

    @Override
    public void visit(NumberLiteralToken numberASTNode) {
        operationStack.pushToken(numberASTNode, false);
    }


}
