package calculator.rpn;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum  Operator {

    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    SQRT("sqrt"),
    UNDO("undo"),
    CLEAR("clear");

    @Getter
    private String image;


    Operator(String image) {
        this.image = image;
    }

    public static Optional<Operator> getOperator(String image) {
        return Arrays.stream(Operator.values()).filter(op->op.getImage().equals(image)).findFirst();
    }

    public static boolean isOperator(String image) {
        return getOperator(image).isPresent();
    }

}
