package functional.donut_shop;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Payment {

    protected final CreditCard creditCard;
    protected final int amount;

    public Payment(CreditCard creditCard, int amount) {
        this.creditCard = creditCard;
        this.amount = amount;
    }

    public Payment combine(Payment payment) {
        if (creditCard.equals(payment.creditCard)) {
            return new Payment(creditCard, amount + payment.amount);
        } else {
            throw new IllegalStateException("Different credit cards used");
        }
    }

    public static List<Payment> groupByCard(List<Payment> payments) {
        return payments.stream()
                .collect(Collectors.groupingBy(Payment::getCreditCard))
                .values()
                .stream()
                .map(x -> x.stream().reduce(Payment::combine))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }
}
