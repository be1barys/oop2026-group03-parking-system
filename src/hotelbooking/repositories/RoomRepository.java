package hotelbooking.repositories;

import hotelbooking.entities.Room;
import java.time.LocalDate;
import java.util.List;

public interface RoomRepository  extends Dao<Room, Integer> {
    List<Room> findAvailableRooms(LocalDate in, LocalDate out);

}
