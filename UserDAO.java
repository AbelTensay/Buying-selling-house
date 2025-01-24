package buyingandselling;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static UserDAO instance;
    private Connection connection;

    private UserDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bas", "root", ""); // Adjust credentials as needed
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public void registerUser(User user) {
        String sql = "INSERT INTO Users (username, password, role, Phone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
            
            // Set phone number based on user type
            if (user instanceof Owner) {
                pstmt.setString(4, ((Owner) user).getPhoneNumber());
            } else if (user instanceof Customer) {
                pstmt.setString(4, ((Customer) user).getPhoneNumber());
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String role = rs.getString("role");
                return createUserFromResultSet(rs); // Use createUserFromResultSet for proper object creation
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private User createUserFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String role = rs.getString("role");

        switch (role) {
            case "Admin":
                return new Admin(id, username, password);
            case "Owner":
                String phoneNumber = rs.getString("Phone");
                return new Owner(id, username, password, phoneNumber);
            case "Customer":
                phoneNumber = rs.getString("Phone");
                return new Customer(id, username, password, phoneNumber);
            default:
                return null; // Or throw an exception for unknown role
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                User user = createUserFromResultSet(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM Users WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return createUserFromResultSet(rs); // Use createUserFromResultSet for proper object creation
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to get an Owner by their ID
    public Owner getOwnerById(int id) {
        String sql = "SELECT * FROM Users WHERE id = ? AND role = 'Owner'";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Owner(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("Phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no owner is found
    }

    // Method to get a Customer by their ID
    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM Users WHERE id = ? AND role = 'Customer'";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("Phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no customer is found
    }
}
