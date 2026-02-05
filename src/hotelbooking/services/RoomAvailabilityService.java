package hotelbooking.services;


import hotelbooking.entities.Room;
import hotelbooking.exceptions.InvalidDateRangeException;
import hotelbooking.repositories.RoomRepository;

import java.time.LocalDate;
import java.util.List;

public class RoomAvailabilityService {
    private final RoomRepository roomRepository;

    public RoomAvailabilityService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    public List<Room> search(LocalDate checkIn, LocalDate checkOut) throws InvalidDateRangeException {
        if (!checkIn.isBefore(checkOut)) {
            throw new InvalidDateRangeException("Invalid date range (check-in must be before check-out)#1.");
        }
        return roomRepository.findAvailableRooms(checkIn, checkOut);
    }
}
