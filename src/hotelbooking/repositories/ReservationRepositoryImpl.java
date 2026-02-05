package hotelbooking.repositories;

import edu.aitu.oop3.db.DatabaseConnection;
import hotelbooking.entities.Reservation;

import java.sql.*;

public class ReservationRepositoryImpl implements ReservationRepository {

    @Override
    public int save(Reservation r) {
        String sql = """
            INSERT INTO reservations (guest_id, room_id, check_in, check_out, status, total_price, deposit_paid)
            VALUES (?, ?, ?, ?, 'ACTIVE! ', ?, ?)
            RETURNING id
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getGuestId());
            ps.setInt(2, r.getRoomId());
            ps.setDate(3, Date.valueOf(r.getCheckIn()));
            ps.setDate(4, Date.valueOf(r.getCheckOut()));
            ps.setDouble(5, r.getTotalPrice());
            ps.setDouble(6, r.getDepositPaid());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Reservation findById(int reservationId) {
        String sql = "SELECT * FROM reservations WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reservationId);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                Reservation r = new Reservation();
                r.setId(rs.getInt("id"));
                r.setGuestId(rs.getInt("guest_id"));
                r.setRoomId(rs.getInt("room_id"));
                r.setCheckIn(rs.getDate("check_in").toLocalDate());
                r.setCheckOut(rs.getDate("check_out").toLocalDate());
                r.setStatus(rs.getString("status"));
                r.setTotalPrice(rs.getDouble("total_price"));
                r.setDepositPaid(rs.getDouble("deposit_paid"));
                return r;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cancel(int reservationId) {
        String sql = "UPDATE reservations SET status='CANCELLED' WHERE id=? AND status <> 'CANCELLED'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reservationId);
            int updated = ps.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
