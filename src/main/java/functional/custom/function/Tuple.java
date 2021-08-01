package functional.custom.function;

public class Tuple<A, B> {

    private final A left;
    private final B right;

    private Tuple(A left, B right) {
        this.left = left;
        this.right = right;
    }

    public static <A, B> Tuple<A, B> of(A left, B right) {
        return new Tuple<>(left, right);
    }

    public A getLeft() {
        return left;
    }

    public B getRight() {
        return right;
    }
}
