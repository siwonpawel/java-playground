package functional.donut_shop;

public interface Result<T> {

    void bind(Effect<T> success, Effect<String> failure);

    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    static <T> Result<T> failure(String message) {
        return new Failure(message);
    }

    static class Success<T> implements Result<T> {
        T value;

        public Success(T value) {
            this.value = value;
        }

        @Override
        public void bind(Effect<T> success, Effect<String> failure) {
            success.apply(value);
        }
    }

    static class Failure<T> implements Result<T> {
        String value;

        public Failure(String message) {
            this.value = message;
        }

        @Override
        public void bind(Effect<T> success, Effect<String> failure) {
            failure.apply(value);
        }
    }


}
