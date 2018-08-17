import java.math.BigDecimal;
import java.math.BigInteger;

public class Arithmetic {

    private class NullOperand extends ArithmeticException{}

    private boolean isDouble(Number value) {

        return value != null && value instanceof Double;
    }

    private boolean isIntOrLong(Number value) {
        return value !=null && (value instanceof Integer || value instanceof  Long);
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
            Double value =  new BigDecimal((Double) left).add(new BigDecimal((Double) right)).doubleValue();
            return value;
        }

        if (isIntOrLong(left) || isIntOrLong(right)) {

            return BigInteger.valueOf((Long) left).add(BigInteger.valueOf((Long) right)).longValue();
        }

        throw new IllegalArgumentException("parameter should be an integer or a long or a double type");
    }

    private   Number sub(Number left, Number right) {

        if (left == null || right == null) {
            controlNullOperand();
        }
        if (isDouble(left) || isDouble(right)) {
            Double value =  new BigDecimal((Double) left).subtract(new BigDecimal((Double) right)).doubleValue();
            return value;
        }
        if (isIntOrLong(left) || isIntOrLong(right)) {

           return  BigInteger.valueOf((Long) left).subtract(BigInteger.valueOf((Long) right)).longValue();
        }
        throw new IllegalArgumentException("parameter should be an integer or a long or a double type");
    }

    private   Number mul(Number left, Number right) {

        if (left == null || right == null) {
            controlNullOperand();
        }
        if (isDouble(left) || isDouble(right)) {
            Double value =  new BigDecimal((Double) left).multiply(new BigDecimal((Double) right)).doubleValue();
            return value;
        }
        if (isIntOrLong(left) || isIntOrLong(right)) {

           return  BigInteger.valueOf((Long) left).multiply(BigInteger.valueOf((Long) right)).longValue();
        }
        throw new IllegalArgumentException("parameter should be an integer or a long or a double type");
    }


    private   Number div(Number left, Number right) {

        if (left == null || right == null) {
            controlNullOperand();
        }
        try {
            if (isDouble(left) || isDouble(right)) {
                Double value = new BigDecimal((Double) left).divide(new BigDecimal((Double) right)).doubleValue();
                return value;
            }
            if (isIntOrLong(left) || isIntOrLong(right)) {

                Long value = BigInteger.valueOf((Long) left).divide(BigInteger.valueOf((Long) right)).longValue();
                return value;
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
