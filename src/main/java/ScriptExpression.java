import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScriptExpression implements Expression {


    private List<SimpleNode> nodes = new ArrayList<>();

    private Interpreter interpreter;

    ScriptExpression(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    @Override
    public String getDisplayValue() {
        interpreter.interpreter(this);
        return interpreter.getDisplayValue();
    }


}
