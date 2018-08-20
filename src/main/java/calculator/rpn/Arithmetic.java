package calculator.rpn;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Arithmetic {

    private class NullOperand extends ArithmeticException {
    }

    private boolean isLong(Number value) {
        return value != null && value instanceof Long;
    }

    private boolean isDouble(Number value) {

        return value != null && value instanceof Double;
    }

    private Integer toInteger(Number value) {
        if (value instanceof Integer) {
            return (Integer) value;
        }
        throw new ClassCastException(String.format("Can not cast %s to a Integer ", value.getClass()));
    }


    private Long toLong(Number value) {
        if (value instanceof Integer) {
            return Long.valueOf(value.toString());
        } else if (value instanceof Long) {
            return (long) value;
        }
        throw new ClassCastException(String.format("Can not cast %s to a Long ", value.getClass()));
    }


    private Double toDouble(Number value) {
        if (value instanceof Long || value instanceof Integer) {
            return Double.valueOf(value.toString());
        } else if (value instanceof Double) {
            return (Double) value;
        }
        throw new ClassCastException(String.format("Can not cast %s to a Double ", value.getClass()));
    }

    private BigDecimal toBigDecimal(Number value) {
        if (value instanceof Integer) {
            return BigDecimal.valueOf(Long.valueOf(value.toString()));
        }

        if (value instanceof Long) {
            return BigDecimal.valueOf((long)value);
        }
        if (value instanceof Double) {
            return BigDecimal.valueOf((double) value);
        }
        if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger)value);
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        throw new ClassCastException(String.format("Can not cast %s to a BigDecimal ", value.getClass()));
    }

    private boolean isValidNumber(Number value) {
        return value instanceof Integer
                || value instanceof Long
                || value instanceof Double;
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

    private Number add(Number left, Number right) {
        if (left == null || right == null) {
            controlNullOperand();
        }
        if (!isValidNumber(left) || !isValidNumber(right)) {
            throw new IllegalArgumentException("Invalid number");
        }

        if (isDouble(left) || isDouble(right)) {

            BigDecimal value = toBigDecimal(left).add(toBigDecimal(right));
            if (value.compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) > 0
                    || value.compareTo(BigDecimal.valueOf(Double.MIN_VALUE)) < 0) {
                throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, left, right);
            }
            return value.doubleValue();

        }
        if (isLong(left) || isLong(right)) {
            return ArithmeticUtils.addAndCheck(toLong(left), toLong(right));
        }
        return ArithmeticUtils.addAndCheck(toInteger(left), toInteger(right));

    }

    private Number sub(Number left, Number right) {
        if (left == null || right == null) {
            controlNullOperand();
        }
        if (!isValidNumber(left) || !isValidNumber(right)) {
            throw new IllegalArgumentException("Invalid number");
        }
        if (isDouble(left) || isDouble(right)) {
            BigDecimal value = toBigDecimal(left).subtract(toBigDecimal(right));
            if (value.compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) > 0
                    || value.compareTo(BigDecimal.valueOf(Double.MIN_VALUE)) < 0) {
                throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, left, right);
            }
            return value.doubleValue();
        }

        if (isLong(left) || isLong(right)) {
            return ArithmeticUtils.subAndCheck(toLong(left), toLong(right));
        }
        return ArithmeticUtils.subAndCheck(toInteger(left), toInteger(right));
    }

    private Number mul(Number left, Number right) {
        if (left == null || right == null) {
            controlNullOperand();
        }
        if (!isValidNumber(left) || !isValidNumber(right)) {
            throw new IllegalArgumentException("Invalid number");
        }
        if (isDouble(left) || isDouble(right)) {
            BigDecimal value = toBigDecimal(left).multiply(toBigDecimal(right));
            if (value.compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) > 0
                    || value.compareTo(BigDecimal.valueOf(Double.MIN_VALUE)) < 0) {
                throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, left, right);
            }
            return value.doubleValue();
        }

        if (isLong(left) || isLong(right)) {
            return ArithmeticUtils.mulAndCheck(toLong(left), toLong(right));
        }
        return ArithmeticUtils.mulAndCheck(toInteger(left), toInteger(right));

    }


    private Number div(Number left, Number right) {
        if (left == null || right == null) {
            controlNullOperand();
        }
        if (!isValidNumber(left) || !isValidNumber(right)) {
            throw new IllegalArgumentException("Invalid number");
        }
        if (toDouble(right) == 0) {
            throw new ArithmeticException("Divide by zero");
        }
        return toDouble(left) / toDouble(right);

    }

    public Number sqrt(Number value) {
        if (value == null) {
            controlNullOperand();
        }
        if (!isValidNumber(value)) {
            throw new IllegalArgumentException("Invalid number");
        }

        return Math.sqrt(toDouble(value));
    }

    private void controlNullOperand() {

        throw new NullOperand();

    }
}
