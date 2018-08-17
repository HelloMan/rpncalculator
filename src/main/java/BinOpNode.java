import lombok.Data;

@Data
public class BinOpNode extends SimpleNode {


    private final Operator operator;

    public BinOpNode(int id, Operator operator) {
        super(id);
        this.operator = operator;
    }


    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitBinOp(this);
    }


}
