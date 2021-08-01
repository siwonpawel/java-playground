package functional.donut_shop;

public interface Effect<T> {
    void apply(T t);
}
