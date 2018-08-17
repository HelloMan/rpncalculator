package calculator.rpn;

import org.apache.commons.math.util.MathUtils;

import java.math.BigDecimal;

public class Arithmetic {

    private class NullOperand extends ArithmeticException{}

    private boolean isDouble(Number value) {

        return value != null && value instanceof Double;
    }

    private boolean isIntOrLong(Number value) {
        return value !=null && (value instanceof Integer || value instanceof  Long);
    }

    private Double toDouble(Number value ){

        if (value instanceof Long || value instanceof Integer) {
            return Double.valueOf(value.toString());
        } else if (value instanceof Double) {
            return (Double) value;
        }
        throw new IllegalArgumentException("value should a long or an integer");
    }

    private Long toLong(Number value ){
        if (value instanceof Integer) {
            return Long.valueOf(value.toString());
        }
        if (value instanceof Long) {
            return (Long)value;
        }
        throw new IllegalArgumentException("value should a long or an integer");
    }


    public Number calculate(Number left, Number right, Operator operator) {
        Number value = null;
        switch (operator) {
            case ADD:
                value = add(left, right);
                break;
            case SUB:
                value = sub(left, right);
                break;
            case MUL:
                value = mul(left, right);
                break;

            case DIV:
                value = div(left, right);
                break;
        }

        return value;

    }

    private   Number add(Number left, Number right) {

        if (left == null || right == null) {
            controlNullOperand();
        }
        if (isDouble(left) || isDouble(right)) {
            return  BigDecimal.valueOf( toDouble(left)).add(  BigDecimal.valueOf(toDouble(right))).doubleValue();
        }

        if (isIntOrLong(left) || isIntOrLong(right)) {

            return MathUtils.addAndCheck(toLong(left), toLong(right));
        }

        throw new IllegalArgumentException("parameter should be an integer or a long or a double type");
    }

    private   Number sub(Number left, Number right) {

        if (left == null || right == null) {
            controlNullOperand();
        }
        if (isDouble(left) || isDouble(right)) {
            return  toDouble(left)- toDouble(right) ;
        }
        if (isIntOrLong(left) || isIntOrLong(right)) {

            return MathUtils.subAndCheck(toLong(left), toLong(right));

        }
        throw new IllegalArgumentException("parameter should be an integer or a long or a double type");
    }

    private   Number mul(Number left, Number right) {

        if (left == null || right == null) {
            controlNullOperand();
        }
        if (isDouble(left) || isDouble(right)) {
            return BigDecimal.valueOf(toDouble(left)).multiply( BigDecimal.valueOf(toDouble( right))).doubleValue();
        }
        if (isIntOrLong(left) || isIntOrLong(right)) {

            return MathUtils.mulAndCheck(toLong(left), toLong(right));
        }

        throw new IllegalArgumentException("parameter should be an integer or a long or a double type");
    }


    private   Number div(Number left, Number right) {

        if (left == null || right == null) {
            controlNullOperand();
        }
        try {
            if (isDouble(left) || isDouble(right)) {
                return BigDecimal.valueOf(toDouble( left)).divide(  BigDecimal.valueOf(toDouble( right))).doubleValue();
            }
            if (isIntOrLong(left) || isIntOrLong(right)) {

                return toDouble(left) / toDouble(right);

            }

            throw new IllegalArgumentException("parameter should be an integer or a long or a double type");
        } catch (ArithmeticException e) {
            throw new ExpressionException(e);
        }
    }

    public Number sqrt(Number value) {

        if (value == null) {
            controlNullOperand();
        }

        return Math.sqrt((value).doubleValue());
    }

    private void controlNullOperand() {

        throw new NullOperand();

    }
}
