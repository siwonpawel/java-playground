package functional.donut_shop;

import functional.custom.function.Function;

import java.util.regex.Pattern;

public class EmailValidation {

    static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static Function<String, Result> emailChecker = s ->
            s == null
                    ? new Result.Failure("Email cannot be null") :
                    s.equals("")
                            ? new Result.Failure("Email cannot be empty")
                            : emailPattern.matcher(s).matches()
                            ? new Result.Success() :
                            new Result.Failure("Email address " + s + " is not correct");

    public static void main(String args) {
        validate("to.jest@jakis.adres").exec();
        validate("null").exec();
        validate("").exec();
        validate("jan.kowalski@test.com").exec();
    }

    private static void logError(String s) {
        System.out.println("Błąd: " + s);
    }

    private static void sendVerificationMail(String s) {
        System.out.println("Wysyłam: " + s);
    }

    static Executable validate(String s) {
        Result result = emailChecker.apply(s);
        return result instanceof Result.Success ?
                () -> sendVerificationMail(s) :
                () -> logError(result.getMessage());
    }
}
