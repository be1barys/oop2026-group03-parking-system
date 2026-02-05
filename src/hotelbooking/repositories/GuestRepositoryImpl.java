package hotelbooking.repositories;

import edu.aitu.oop3.db.DatabaseConnection;
import java.sql.*;

public class GuestRepositoryImpl implements GuestRepository {

    @Override //id
    public int getOrCreateGuestId(String name, String email) {
        String findSql = "SELECT id FROM guests WHERE email = ?";
        String insertSql = "INSERT INTO guests(name, email) VALUES (?, ?) RETURNING id";

        try (Connection conn = DatabaseConnection.getConnection()) {

            try (PreparedStatement ps = conn.prepareStatement(findSql)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return rs.getInt("id");
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setString(1, name);
                ps.setString(2, email);
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
