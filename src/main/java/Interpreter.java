import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.stream.Collectors;

@Slf4j
public class Interpreter implements NodeVisitor {


    private Deque<LiteralNode> astNodes = new ArrayDeque<>();

    private Deque<Undo> undoNodes = new ArrayDeque<>();

    public void interpreter(Expression expression) {


        for (SimpleNode node : expression.getNodes()) {
            try {
                node.accept(this);
            } catch (ExpressionException e) {
                log.warn(e.getMessage());
                break;
            }
        }

    }

    public String getDisplayValue() {

        Deque<LiteralNode> nodes = new ArrayDeque<>();

        astNodes.forEach(n -> nodes.push(n));

        return nodes.stream()
                .map(LiteralNode::formatText)
                .collect(Collectors.joining(" "));
    }

    @Override
    public void visitBinOp(BinOpNode binOpNode) {
        LiteralNode left = null;
        LiteralNode right = null;
        Iterator<LiteralNode> nodeIterable = astNodes.iterator();
        if (nodeIterable.hasNext()) {
            right = nodeIterable.next();
        }

        if (nodeIterable.hasNext()) {
            left = nodeIterable.next();
        }

        if (left == null || right == null) {
            throw new ExpressionException(String.format("Operator %s (position:%d): insufficient parameters",
                    binOpNode.getOperator().getOp(), binOpNode.getPosition()));
        }
        Arithmetic arithmetic = new Arithmetic();
        Number value = arithmetic.calculate(left.getLiteral(), right.getLiteral(), binOpNode.getOperator());
        if (value != null) {
            //remove left and right from stack
            astNodes.pop();
            astNodes.pop();

            astNodes.push(new LiteralNode(-1, value, true));

            undoNodes.push(new Undo(right, left));
        }

    }

    @Override
    public void visitUnaryOp(UnaryOpNode unaryOpNode) {
        if (unaryOpNode.getOp() != Operator.SQRT) {
            return;
        }
        LiteralNode node = astNodes.peek();
        if (node != null) {
            astNodes.pop();
            Number value = new Arithmetic().sqrt(node.getLiteral());
            //push back the literal to stack
            astNodes.push(new LiteralNode(-1, value, true));
            undoNodes.push(new Undo(node));
            return ;

        }
        throw new ExpressionException(String.format("Operator %s (position:%d): insufficient parameters",
                unaryOpNode.getOp().getOp(), unaryOpNode.getPosition()));


    }

    @Override
    public void visitUndo(MisOpNode misOpNode) {

        boolean popupCalculateNode = false;
        if (astNodes.peek() != null) {
            popupCalculateNode = astNodes.pop().isCalculated();

        }
        if (popupCalculateNode && undoNodes.peek() != null) {
            Undo undo = undoNodes.pop();
            undo.getNodes().forEach(n -> astNodes.push(n));
        }

    }

    @Override
    public void visitClear(MisOpNode misOpNode) {
        Undo undo = new Undo(astNodes);
        undoNodes.push(undo);
        astNodes.clear();
    }

    @Override
    public void visitLiteral(LiteralNode numberASTNode) {
        astNodes.push(numberASTNode);
    }
}
