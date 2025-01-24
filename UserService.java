package buyingandselling;

import java.util.List;

public class UserService {
    private UserDAO userDAO = UserDAO.getInstance(); // Singleton instance of UserDAO

    public void registerUser(String type, String username, String password, String phoneNumber) {
        User user;
        if (type.equalsIgnoreCase("Owner")) {
            user = new Owner(0, username, password, phoneNumber); // Create Owner with phone number
        } else if (type.equalsIgnoreCase("Customer")) {
            user = new Customer(0, username, password, phoneNumber); // Create Customer with phone number
        } else {
            throw new IllegalArgumentException("Unknown user type: " + type);
        }
        userDAO.registerUser(user); // Register the user in the database
    }

    public User authenticateUser(String username, String password) {
        return userDAO.getUserByUsernameAndPassword(username, password); // Authenticate user
    }

    public User getUserInfo(int userId) {
        return userDAO.getUserById(userId); // Fetch user info by ID
    }
    
    public List<User> getAllUsers() {
        return userDAO.getAllUsers(); // Retrieve all users
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId); // Delete user by ID
    }

    public String getOwnerPhoneById(int ownerId) {
        Owner owner = userDAO.getOwnerById(ownerId); // Retrieve the owner by ID
        if (owner != null) {
            return owner.getPhoneNumber(); // Return the owner's phone number
        } else {
            System.out.println("Owner not found for ID: " + ownerId);
            return null; // Return null if the owner is not found
        }
    }

    public String getCustomerPhoneById(int customerId) {
        Customer customer = userDAO.getCustomerById(customerId); // Retrieve the customer by ID
        if (customer != null) {
            return customer.getPhoneNumber(); // Return the customer's phone number
        } else {
            System.out.println("Customer not found for ID: " + customerId);
            return null; // Return null if the customer is not found
        }
    }
}
