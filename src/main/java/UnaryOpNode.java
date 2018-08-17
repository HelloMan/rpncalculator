import lombok.Getter;

public class UnaryOpNode extends SimpleNode {

    @Getter
    private final Operator op;

    public UnaryOpNode(int id, Operator op) {
        super(id);
        this.op = op;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        if (Operator.SQRT == op) {
            visitor.visitUnaryOp(this);
        }
    }
}
