package hotelbooking.entities.rooms;

import hotelbooking.entities.Room;

public class DormBed extends Room {
    public DormBed(int id, String number, double pricePerNight) {
        super(id, number, "DormBed", pricePerNight);
    }
}
