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

    static <A, B, C> Function<A, C> partialB(B b, Function<A, Function<B, C>> f) {
        return a -> f.apply(a).apply(b);
    }

    static <A, B, C, D> Function<A, Function<B, Function<C, Function<D, String>>>> fourFormatter() {
        return a -> b -> c -> d -> String.format("%s %s %s %s", a, b, c, d);
    }

    static <A, B, C> Function<A, Function<B, C>> tupleResolver(Function<Tuple<A, B>, C> f) {
        return a -> b -> f.apply(Tuple.of(a, b));
    }

    static <T, U, V> Function<U, Function<T, V>> reverseArgs(Function<T, Function<U, V>> f) {
        return u -> t -> f.apply(t).apply(u);
    }

    static Function<Integer, Integer> factorial() {
        return n -> n <= 1 ? n : n * factorial().apply(n -1);
    }

    static <T> Function<T, T> identity() {
        return t -> t;
    }
}

