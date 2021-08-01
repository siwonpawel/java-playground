package functional.donut_shop;

import functional.custom.function.Function;

import java.util.regex.Pattern;

public class EmailValidation {

    static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static Function<String, Result<String>> emailChecker = s ->
            s == null
                    ? new Result.Failure("Email cannot be null") :
                    s.equals("")
                            ? Result.failure("Email cannot be empty")
                            : emailPattern.matcher(s).matches()
                            ? Result.success(s) :
                            Result.failure("Email address " + s + " is not correct");

    public static void main(String args) {
        validate("to.jest@jakis.adres").bind(EmailValidation::sendVerificationMail, EmailValidation::logError);
        validate("null").bind(EmailValidation::sendVerificationMail, EmailValidation::logError);
        validate("").bind(EmailValidation::sendVerificationMail, EmailValidation::logError);
        validate("jan.kowalski@test.com").bind(EmailValidation::sendVerificationMail, EmailValidation::logError);
    }

    private static void logError(String s) {
        System.out.println("Błąd: " + s);
    }

    private static void sendVerificationMail(String s) {
        System.out.println("Wysyłam: " + s);
    }

    static Result<String> validate(String s) {
        return emailChecker.apply(s);
    }
}
