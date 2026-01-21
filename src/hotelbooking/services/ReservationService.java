package hotelbooking.services;

import hotelbooking.entities.Payment;
import hotelbooking.entities.Reservation;
import hotelbooking.entities.Room;
import hotelbooking.exceptions.RoomNotAvailableException;
import hotelbooking.repositories.PaymentRepository;
import hotelbooking.repositories.ReservationRepository;
import hotelbooking.repositories.RoomRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReservationService {
    private final RoomRepository roomRepo;
    private final ReservationRepository reservationRepo;
    private final PaymentRepository paymentRepo;
    private final PaymentService paymentService;

    public ReservationService(RoomRepository roomRepo,
                              ReservationRepository reservationRepo,
                              PaymentRepository paymentRepo,
                              PaymentService paymentService) {
        this.roomRepo = roomRepo;
        this.reservationRepo = reservationRepo;
        this.paymentRepo = paymentRepo;
        this.paymentService = paymentService;
    }

    // Auto-select room (first available), calculate total price, enforce 20% deposit
    public int createReservationAutoRoom(int guestId,
                                         LocalDate checkIn,
                                         LocalDate checkOut,
                                         double depositAmount) throws Exception {

        List<Room> available = roomRepo.findAvailableRooms(checkIn, checkOut);
        if (available.isEmpty()) {
            throw new RoomNotAvailableException("No rooms available for these dates.");
        }

        Room chosen = available.get(0); // auto room
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        double totalPrice = chosen.getPricePerNight() * nights;

        // enforce 20% rule
        paymentService.validateDeposit(depositAmount, totalPrice);

        // save reservation
        Reservation r = new Reservation();
        r.setGuestId(guestId);
        r.setRoomId(chosen.getId());
        r.setCheckIn(checkIn);
        r.setCheckOut(checkOut);
        r.setTotalPrice(totalPrice);
        r.setDepositPaid(depositAmount);
        r.setStatus("ACTIVE");

        int reservationId = reservationRepo.save(r);

        // save payment (deposit)
        Payment p = new Payment();
        p.setReservationId(reservationId);
        p.setAmount(depositAmount);
        p.setStatus("PAID");
        paymentRepo.save(p);

        return reservationId;
    }

    public boolean cancelReservation(int reservationId) {
        return reservationRepo.cancel(reservationId);
    }

    public Reservation getReservation(int reservationId) {
        return reservationRepo.findById(reservationId);
    }
}
