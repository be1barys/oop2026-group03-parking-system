package hotelbooking.repositories;

import edu.aitu.oop3.db.DatabaseConnection;
import hotelbooking.entities.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentRepositoryImpl implements PaymentRepository {

    @Override
    public void save(Payment p) {
        String sql = "INSERT INTO payments (reservation_id, amount, status) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getReservationId());
            ps.setDouble(2, p.getAmount());
            ps.setString(3, p.getStatus());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
