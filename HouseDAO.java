package buyingandselling;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HouseDAO {
    private static HouseDAO instance;
    private Connection connection;

    private HouseDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bas", "root", ""); // Adjust credentials as needed
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized HouseDAO getInstance() {
        if (instance == null) {
            instance = new HouseDAO();
        }
        return instance;
    }

    public void addHouse(House house) {
        String sql = "INSERT INTO Houses (ownerId, address, price, size, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, house.getOwnerId());
            pstmt.setString(2, house.getAddress());
            pstmt.setDouble(3, house.getPrice());
            pstmt.setInt(4, house.getSize());
            pstmt.setString(5, house.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public House getHouseById(int id) {
        String sql = "SELECT * FROM Houses WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new House(rs.getInt("id"), rs.getInt("ownerId"), rs.getString("address"), rs.getDouble("price"), rs.getInt("size"), rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<House> getAvailableHouses() {
        List<House> houses = new ArrayList<>();
        String sql = "SELECT * FROM Houses WHERE status = 'Available'";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                houses.add(new House(
                    rs.getInt("id"),
                    rs.getInt("ownerId"),
                    rs.getString("address"),
                    rs.getDouble("price"),
                    rs.getInt("size"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houses;
    }

    public void deleteHouse(int houseId) {
        String sql = "DELETE FROM Houses WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, houseId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("House deleted successfully.");
            } else {
                System.out.println("No house found with ID: " + houseId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
