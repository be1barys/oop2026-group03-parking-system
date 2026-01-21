package hotelbooking.repositories;

import edu.aitu.oop3.db.DatabaseConnection;
import hotelbooking.entities.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    @Override
    public List<Room> findAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        List<Room> rooms = new ArrayList<>();

        String sql = """
            SELECT r.*
            FROM rooms r
            WHERE r.id NOT IN (
                SELECT room_id
                FROM reservations
                WHERE NOT (check_out <= ? OR check_in >= ?)
                  AND (status IS NULL OR status <> 'CANCELLED')
            )
            ORDER BY r.id
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(checkIn));
            ps.setDate(2, Date.valueOf(checkOut));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rooms.add(new Room(
                            rs.getInt("id"),
                            rs.getString("number"),
                            rs.getString("type"),
                            rs.getDouble("price_per_night")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rooms;
    }
}
