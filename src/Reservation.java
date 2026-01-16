import java.time.LocalDate;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private UUID guestId;
    private UUID roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}