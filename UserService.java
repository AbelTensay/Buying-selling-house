package buyingandselling;

public class UserService {
    private UserDAO userDAO = UserDAO.getInstance(); // Assuming UserDAO manages user data

    public void registerUser(String type, String username, String password) {
        User user = null;
        if (type.equalsIgnoreCase("Customer")) {
            user = new Customer(0, username, password); // ID will be auto-generated
        } else if (type.equalsIgnoreCase("Owner")) {
            user = new Owner(0, username, password); // ID will be auto-generated
        }
        if (user != null) {
            userDAO.addUser(user);
        }
    }

    public User authenticateUser(String username, String password) {
        return userDAO.getUserByUsernameAndPassword(username, password);
    }

    public User getUserInfo(int userId) {
        return userDAO.getUserById(userId); // Fetch user info by ID
    }
}