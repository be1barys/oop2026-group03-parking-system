package hotelbooking.entities;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private int guestId;
    private int roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double totalPrice;
    private double depositPaid;
    private String status;

    public Reservation() {
    }

    public Reservation(int id, int guestId, int roomId,
                       LocalDate checkIn, LocalDate checkOut,double totalPrice, double depositPaid,String status) {
        this.id = id;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id;}


    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public double getDepositPaid() { return depositPaid; }
    public void setDepositPaid(double depositPaid) { this.depositPaid = depositPaid; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() { //return
        return "hotelbooking.entities.Reservation{id=" + id + ", guestId=" + guestId +
                ", roomId=" + roomId + ", checkIn=" + checkIn +
                ", checkOut=" + checkOut + " , totalPrice= " + totalPrice + " , depositPaid= " + depositPaid + " , status= " + status + "}";
    }
}