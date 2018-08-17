package calculator.rpn;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultOperationStack implements OperationStack {

    //the numbers on the stack
    @Getter(AccessLevel.PACKAGE)
    private final Deque<NumberLiteralToken> tokens = new ArrayDeque<>();

    @Getter(AccessLevel.PACKAGE)
    private final Deque<UndoAction> undoActions = new ArrayDeque<>();

    @Override
    public void pushToken(NumberLiteralToken token,boolean calculated) {
        tokens.push(token);
        if (!calculated) {
            undoActions.push(UndoAction.DUMMY_UNDO_ACTION);
        }
    }

    @Override
    public Optional<Pair<NumberLiteralToken>> popTokens(BinaryOperatorToken token) {
        NumberLiteralToken left = null;
        NumberLiteralToken right = null;
        if (tokens.peek() != null) {
            right = tokens.pop();
        }
        if (tokens.peek() != null) {
            left = tokens.pop();
        }

        //if left or right is null ,we have to pushToken back to stack
        if (left == null || right == null) {
            if (left != null) {
                tokens.push(left);
            }
            if (right != null) {
                tokens.push(right);
            }
            return Optional.empty();
        }
        undoActions.push(UndoAction.create(right,left));

        return Optional.of(Pair.of(left, right));

    }

    @Override
    public List<NumberLiteralToken> popTokens(FunctionToken token) {
        if (token.getOperator() == Operator.SQRT) {
            if (tokens.peek() != null) {
                NumberLiteralToken numberLiteralToken = tokens.pop();
                undoActions.push(UndoAction.create(numberLiteralToken));


                List<NumberLiteralToken> list = new ArrayList<>(1);
                list.add(numberLiteralToken);
                return list;
            }
        }
        return Collections.emptyList();
    }


    @Override
    public String printStack() {

        Deque<NumberLiteralToken> printTokens = new ArrayDeque<>();

        tokens.forEach(printTokens::push);

        return printTokens.stream()
                .map(NumberLiteralToken::toString)
                .collect(Collectors.joining(" "));
    }


    @Override
    public void clear() {
        undoActions.push(UndoAction.create(tokens));
        tokens.clear();
    }

    @Override
    public void undo() {
        if (tokens.peek() != null) {
            tokens.pop();
        }
        if (undoActions.peek() != null) {
            UndoAction undoAction = undoActions.pop();
            undoAction.undoTokens.forEach(tokens::push);
        }
    }


    protected static class UndoAction {

        private static final UndoAction DUMMY_UNDO_ACTION = new UndoAction();

        @Getter
        private final Deque<NumberLiteralToken> undoTokens = new ArrayDeque<>();

        static UndoAction create(NumberLiteralToken... tokens) {
            UndoAction undoAction = new UndoAction();
            Arrays.stream(tokens).forEach(undoAction.undoTokens::push);
            return undoAction;
        }

        static UndoAction create(Collection<NumberLiteralToken> tokens) {
            UndoAction undoAction = new UndoAction();
            tokens.forEach(undoAction.undoTokens::push);
            return undoAction;
        }
    }
}
