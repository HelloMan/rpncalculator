import lombok.Getter;

public abstract class SimpleNode {

    @Getter
    private final int position;

    protected SimpleNode(int position) {
        this.position = position;
    }

    abstract void accept(NodeVisitor visitor);
}
