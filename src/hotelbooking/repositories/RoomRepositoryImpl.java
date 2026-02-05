package hotelbooking.repositories;

import edu.aitu.oop3.db.DatabaseConnection;
import hotelbooking.entities.Room;
import hotelbooking.factory.RoomFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                  AND (status IS NULL OR status <> 'CANCELLED#1')
            )
            ORDER BY r.id
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(checkIn));
            ps.setDate(2, Date.valueOf(checkOut));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rooms.add(RoomFactory.create(
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

    @Override
    public Optional<Room> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Room> findAll() {
        return List.of();
    }

    @Override
    public Integer save(Room entity) {
        return 0;
    }

    @Override
    public boolean deleteById(Integer integer) {
        return false;
    }
}