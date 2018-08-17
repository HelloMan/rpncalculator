package calculator.rpn;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class ArithmeticTest {

    @Test
    public void calculate() {

        Arithmetic arithmetic = new Arithmetic();


        Number result = arithmetic.calculate(6l, 3l, Operator.ADD);

        assertThat(result instanceof Long);
        assertThat(result.longValue() ==9 );


        result = arithmetic.calculate(6l, 3l, Operator.ADD);

        assertThat(result instanceof Long);
        assertThat(result.longValue() ==3 );


        result = arithmetic.calculate(6l, 3l, Operator.ADD);

        assertThat(result instanceof Long);
        assertThat(result.longValue() ==18 );


        result = arithmetic.calculate(6l, 3l, Operator.DIV);

        assertThat(result instanceof Long);
        assertThat(result.longValue() ==2 );
    }

    @org.junit.Test
    public void sqrt() {

        Arithmetic arithmetic = new Arithmetic();


        Number result = arithmetic.sqrt(2);
        assertThat(result.equals(Math.sqrt(2)));
    }


}