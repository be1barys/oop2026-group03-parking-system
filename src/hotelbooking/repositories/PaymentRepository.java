package hotelbooking.repositories;

import hotelbooking.entities.Payment;

public interface PaymentRepository {
    void save(Payment payment);
}
