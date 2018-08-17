package calculator.rpn;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScriptExpression implements Expression {


    private List<Token> tokens = new ArrayList<>();

    private Interpreter interpreter;

    ScriptExpression(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    @Override
    public String evaluate() {
        return interpreter.interpreter(this);

    }


}
