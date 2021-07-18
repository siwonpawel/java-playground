package functional.custom.function;

@FunctionalInterface
public interface Function<T, U> {

    U apply(T arg);

    static <I, N, M> Function<I, M> compose(Function<N, M> f1, Function<I, N> f2) {
        return arg -> f1.apply(f2.apply(arg));
    }

    static <T, U, V> Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> higherCompose() {
        return x -> y -> z -> x.apply(y.apply(z));
    }

    static <T, U, V> Function<Function<T, U>, Function<Function<U, V>, Function<T,V>>> higherAndThen() {
        return x -> y -> z -> y.apply(x.apply(z));
    }
}

