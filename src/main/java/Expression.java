import java.util.List;

public interface Expression {

    /**
     *
     * @return original
     */
    String getDisplayValue();

    List<SimpleNode> getNodes();
}
