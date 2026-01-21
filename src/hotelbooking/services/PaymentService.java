package hotelbooking.services;

import hotelbooking.exceptions.PaymentDeclinedException;


public class PaymentService {

    public void validateDeposit(double depositAmount, double totalPrice) throws PaymentDeclinedException {
        double minDeposit = totalPrice * 0.20;


        if (depositAmount < minDeposit) {
            throw new PaymentDeclinedException(
                    "Payment declined. Minimum deposit is 20% = " + minDeposit
            );
        }
    }
}
