package functional.custom.function;

@FunctionalInterface
public interface Function<T, U> {

    U apply(T arg);

    static <I, N, M> Function<I, M> compose(Function<N, M> f1, Function<I, N> f2) {
        return arg -> f1.apply(f2.apply(arg));
    }
}

