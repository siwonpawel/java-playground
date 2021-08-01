package functional.donut_shop;

public class Result {

    protected String message;

    private Result() {

    }

    public String getMessage() {
        return message;
    }

    private Result(String message) {
        this.message = message;
    }

    public static class Success extends Result {

    }

    public static class Failure extends Result {

        public Failure(String message) {
            super(message);
        }
    }


}
