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

    public boolean isOp(){
        return this ==  ADD || this == SUB || this == MUL || this == DIV || this == SQRT;
    }

    public boolean isBinOp(){
        return  this ==  ADD || this == SUB || this == MUL || this == DIV;
    }

    public boolean isUnaryOp(){
        return this == SQRT;
    }



    @Getter
    private String op;


    Operator(String op) {
        this.op = op;
    }

    public static Optional<Operator> getOp(String operator) {
        return Arrays.stream(Operator.values()).filter(op->op.getOp().equalsIgnoreCase(operator)).findFirst();
    }

    public static boolean isOp(String operator) {
        return getOp(operator).isPresent();
    }

}
