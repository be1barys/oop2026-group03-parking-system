package hotelbooking.repositories;

public interface GuestRepository {
    int getOrCreateGuestId(String name, String email);
}
