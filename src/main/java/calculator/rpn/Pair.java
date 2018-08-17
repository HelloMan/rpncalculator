package calculator.rpn;

import lombok.Data;

@Data
public final class Pair<T> {

    private final T first;

    private final T second;

    private Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public static <T> Pair of(T first, T second) {
        return new Pair<T>(first, second);
    }
}
