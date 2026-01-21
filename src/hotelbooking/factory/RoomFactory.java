package hotelbooking.factory;

import hotelbooking.entities.Room;
import hotelbooking.entities.rooms.DormBed;
import hotelbooking.entities.rooms.StandardRoom;
import hotelbooking.entities.rooms.Suite;

public class RoomFactory {
    public static Room create(int id, String number, String type, double pricePerNight) {
        if (type == null) return new Room(id, number, "Unknown", pricePerNight);

        return switch (type.toLowerCase()) {
            case "standard", "single", "double" -> new StandardRoom(id, number, pricePerNight);
            case "suite" -> new Suite(id, number, pricePerNight);
            case "dormbed", "dorm", "bed" -> new DormBed(id, number, pricePerNight);
            default -> new Room(id, number, type, pricePerNight);
        };
    }
}
