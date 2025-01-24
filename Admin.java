package buyingandselling;

import java.util.List;

public class Admin extends User {
    public Admin(int id, String username, String password) {
        super(id, username, password, "Admin");
    }

    public void viewAllUsers(UserService userService) {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername() + ", Role: " + user.getRole());
        }
    }

    public void viewAllHouses(HouseService houseService) {
        List<House> houses = houseService.getAvailableHouses();
        for (House house : houses) {
            System.out.println("ID: " + house.getId() + ", Address: " + house.getAddress() + ", Price: $" + house.getPrice() + ", Status: " + house.getStatus());
        }
    }

    public void deleteUser(UserService userService, int userId) {
        userService.deleteUser(userId);
        System.out.println("User with ID " + userId + " has been deleted.");
    }

    public void deleteHouse(HouseService houseService, int houseId) {
        houseService.deleteHouse(houseId);
        System.out.println("House with ID " + houseId + " has been deleted.");
    }
}
