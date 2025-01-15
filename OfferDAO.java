package buyingandselling;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferDAO {
    private static OfferDAO instance;
    private Connection connection;

    private OfferDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bas", "root", ""); // Adjust credentials as needed
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized OfferDAO getInstance() {
        if (instance == null) {
            instance = new OfferDAO();
        }
        return instance;
    }

    public void addOffer(Offer offer) {
        String sql = "INSERT INTO Offers (houseId, customerId, offerPrice, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, offer.getHouseId());
            pstmt.setInt(2, offer.getCustomerId());
            pstmt.setDouble(3, offer.getOfferPrice());
            pstmt.setString(4, offer.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Offer getOfferById(int id) {
        String sql = "SELECT * FROM Offers WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Offer(rs.getInt("id"), rs.getInt("houseId"), rs.getInt("customerId"), rs.getDouble("offerPrice"), rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Offer> getOffersByOwnerId(int ownerId) {
    List<Offer> offers = new ArrayList<>();
    String sql = "SELECT o.* FROM Offers o JOIN Houses h ON o.houseId = h.id WHERE h.ownerId = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, ownerId);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            offers.add(new Offer(
                rs.getInt("id"),
                rs.getInt("houseId"),
                rs.getInt("customerId"),
                rs.getDouble("offerPrice"),
                rs.getString("status")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return offers;  // Ensure this returns the list
}
}