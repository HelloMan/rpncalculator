package calculator.rpn;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.assertj.core.api.Assertions.assertThat;
public class DefaultTokenStackTest {

    @Test
    public void push() {

        DefaultTokenStack res = new DefaultTokenStack();
        res.push(new NumberLiteralToken(1, "3"));
        assertThat(res.getTokens().size()).isEqualTo(1);
    }

    @Test
    public void pop() {

        DefaultTokenStack res = new DefaultTokenStack();
        res.push(new NumberLiteralToken(1, "3"));

        res.pop();
        assertThat(res.getTokens().size()).isEqualTo(0);
    }

    @Test
    public void printStack() {

        DefaultTokenStack res = new DefaultTokenStack();
        res.push(new NumberLiteralToken(1, "3"));


        assertThat(res.printStack()).isEqualTo("3");
    }

    @Test
    public void iterator() {

        DefaultTokenStack res = new DefaultTokenStack();
        res.push(new NumberLiteralToken(1, "3"));


        assertThat(res.iterator().hasNext()).isTrue();
    }

    @Test
    public void clear() {


        DefaultTokenStack res = new DefaultTokenStack();
        res.push(new NumberLiteralToken(1, "3"));


        res.clear();
        assertThat(res.iterator().hasNext()).isFalse();
    }

    @Test
    public void peek() {

        DefaultTokenStack res = new DefaultTokenStack();
        res.push(new NumberLiteralToken(1, "3"));


        res.peek();
        assertThat(res.iterator().hasNext()).isTrue();
    }

    @Test
    public void getTokens() {

        DefaultTokenStack res = new DefaultTokenStack();
        res.push(new NumberLiteralToken(1, "3"));


        res.peek();
        assertThat(res.getTokens().size()).isEqualTo(1);
    }
}