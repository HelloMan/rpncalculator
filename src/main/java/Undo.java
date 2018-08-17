import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


@Getter
public class Undo {

    private Deque<LiteralNode> nodes = new ArrayDeque<>();

    public static final Undo DUMMY = new Undo();

    public Undo(LiteralNode... nodearr) {
        Arrays.stream(nodearr).forEach(n -> nodes.push(n));
    }

    public Undo(Deque<LiteralNode> nodeStack) {
        nodeStack.forEach(n -> nodes.push(n));
    }
}
