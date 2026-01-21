package hotelbooking.repositories;

import hotelbooking.entities.Reservation;

public interface ReservationRepository {
    int save(Reservation reservation);           // returns reservation id
    boolean cancel(int reservationId);           // true if cancelled, false if not found
    Reservation findById(int reservationId);     // needed to show 20% fee on cancel
}
