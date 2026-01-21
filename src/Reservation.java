import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    private int id;
    private int guestId;
    private int roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation() {
    }

    public Reservation(int id, int guestId, int roomId,
                       LocalDate checkIn, LocalDate checkOut) {
        this.id = id;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return "Reservation{id=" + id + ", guestId=" + guestId +
                ", roomId=" + roomId + ", checkIn=" + checkIn +
                ", checkOut=" + checkOut + "}";
    }
}