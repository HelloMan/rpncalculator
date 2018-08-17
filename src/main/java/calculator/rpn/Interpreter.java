package calculator.rpn;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

/**
 * Token interpreter ,parse token and operate numbers on token stack
 */
@Slf4j
public class Interpreter implements TokenVisitor {


    @Getter
    private final TokenStack tokenStack;

    private final UndoManager undoManager ;

    public Interpreter(){
        tokenStack = new DefaultTokenStack();
        undoManager = new UndoManager(tokenStack);
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
        return tokenStack.printStack();

    }


    @Override
    public void visit(BinaryOperatorToken token) {
        NumberLiteralToken left = null;
        NumberLiteralToken right = null;
        Iterator<NumberLiteralToken> nodeIterable = tokenStack.iterator();
        if (nodeIterable.hasNext()) {
            right = nodeIterable.next();
        }

        if (nodeIterable.hasNext()) {
            left = nodeIterable.next();
        }

        if (left == null || right == null) {
            throw new ExpressionException(String.format("Operator %s (position:%d): insufficient parameters",
                    token.getOperator().getImage(), token.getPosition()));
        }
        Arithmetic arithmetic = new Arithmetic();
        Number value = arithmetic.calculate(left.getLiteral(), right.getLiteral(), token.getOperator());
        if (value != null) {
            //popup left and right from stack
            tokenStack.pop();
            tokenStack.pop();
            tokenStack.push(new NumberLiteralToken(-1, value, true));
            undoManager.apply(right,left);
        }

    }

    @Override
    public void visit(FunctionToken token) {
        if (token.getOperator() != Operator.SQRT) {
            return;
        }
        NumberLiteralToken node = tokenStack.peek();
        if (node != null) {
            tokenStack.pop();
            Number value = new Arithmetic().sqrt(node.getLiteral());
            //push back the literal to stack
            tokenStack.push(new NumberLiteralToken(-1, value, true));
            undoManager.apply(node);
            return ;

        }
        throw new ExpressionException(String.format("Operator %s (position:%d): insufficient parameters",
                token.getOperator().getImage(), token.getPosition()));


    }

    @Override
    public void visit(UndoToken token) {
       undoManager.undo();
    }

    @Override
    public void visit(ClearToken token) {
        undoManager.apply(tokenStack.getTokens());
        tokenStack.clear();
    }

    @Override
    public void visit(NumberLiteralToken numberASTNode) {
        tokenStack.push(numberASTNode);
        undoManager.applyEmpty();
    }


}
