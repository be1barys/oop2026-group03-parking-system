package hotelbooking.dto;

import hotelbooking.entities.Room;

import java.time.LocalDate;

public class ReservationDetails {

    private final int guestId;
    private final Room room;
    private final LocalDate checkIn;
    private final LocalDate checkOut;

    // optional "options" fields (keep simple, can be default values)
    private final long nights;
    private final double totalPrice;
    private final double depositPaid;

    private ReservationDetails(Builder b) {
        this.guestId = b.guestId;
        this.room = b.room;
        this.checkIn = b.checkIn;
        this.checkOut = b.checkOut;
        this.nights = b.nights;
        this.totalPrice = b.totalPrice;
        this.depositPaid = b.depositPaid;
    }

    public int getGuestId() { return guestId; }
    public Room getRoom() { return room; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public long getNights() { return nights; }
    public double getTotalPrice() { return totalPrice; }
    public double getDepositPaid() { return depositPaid; }

    public static class Builder {
        private int guestId;
        private Room room;
        private LocalDate checkIn;
        private LocalDate checkOut;

        private long nights;
        private double totalPrice;
        private double depositPaid;

        public Builder guestId(int guestId) {
            this.guestId = guestId;
            return this;
        }

        public Builder room(Room room) {
            this.room = room;
            return this;
        }

        public Builder checkIn(LocalDate checkIn) {
            this.checkIn = checkIn;
            return this;
        }

        public Builder checkOut(LocalDate checkOut) {
            this.checkOut = checkOut;
            return this;
        }

        public Builder nights(long nights) {
            this.nights = nights;
            return this;
        }

        public Builder totalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder depositPaid(double depositPaid) {
            this.depositPaid = depositPaid;
            return this;
        }

        public ReservationDetails build() {
            if (room == null) {
                throw new IllegalStateException("Room is required");
            }
            if (checkIn == null || checkOut == null) {
                throw new IllegalStateException("Dates are required");
            }
            if (!checkIn.isBefore(checkOut)) {
                throw new IllegalStateException("Invalid date range");
            }
            return new ReservationDetails(this);
        }
    }
}
