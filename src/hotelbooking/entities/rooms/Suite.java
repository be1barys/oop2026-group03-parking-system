package hotelbooking.entities.rooms;

import hotelbooking.entities.Room;

public class Suite extends Room {
    public Suite(int id, String number, double pricePerNight) {
        super(id, number, "Suite", pricePerNight);
    }
}
