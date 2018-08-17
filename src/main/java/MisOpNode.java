public class MisOpNode extends SimpleNode {

    private final  Operator operator;

    public MisOpNode(int id,Operator operator) {
        super(id);
        this.operator = operator;
    }

    public String toString(){
        return operator.getOp();
    }

    @Override
    void accept(NodeVisitor visitor) {

        if (Operator.CLEAR == operator) {

            visitor.visitClear(this);
        } else if (Operator.UNDO == operator) {
            visitor.visitUndo(this);

        }

    }
}
