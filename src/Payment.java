import java.math.BigDecimal;
import java.util.UUID;

public class Payment {
    private UUID id;
    private UUID reservationId;
    private BigDecimal amount;
    private String status;
}