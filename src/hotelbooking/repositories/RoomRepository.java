package hotelbooking.repositories;

import hotelbooking.entities.Room;
import java.time.LocalDate;
import java.util.List;

public interface RoomRepository {
    List<Room> findAvailableRooms(LocalDate checkIn, LocalDate checkOut);
}
