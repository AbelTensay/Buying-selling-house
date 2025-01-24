package buyingandselling;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static UserService userService = new UserService();
    private static HouseService houseService = new HouseService();
    private static OfferService offerService = new OfferService();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser; // Store the logged-in user

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Real Estate Management System ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login(); // Prompt user to log in
                    break;
                case 2:
                    register(); // Register new user
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    private static void login() {
        System.out.println("--- Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        currentUser = userService.authenticateUser(username, password);
        
        if (currentUser != null) {
            System.out.println("Authentication successful! Welcome, " + currentUser.getUsername() + "!");
            showUserInfo(); // Show user info after login
            navigateToUserRolePage(); // Navigate to the role-specific page
        } else {
            System.out.println("Authentication failed. Please check your credentials.");
        }
    }

    private static void register() {
    System.out.print("Enter user type (Customer, Owner): ");
    String type = scanner.nextLine();
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();
    
    String phoneNumber = null;
    if (type.equalsIgnoreCase("Owner")) {
        System.out.print("Enter phone number: ");
        phoneNumber = scanner.nextLine();
    }

    userService.registerUser(type, username, password, phoneNumber);
    System.out.println(type + " registered successfully!");
}

    private static void showUserInfo() {
        if (currentUser != null) {
            System.out.println("User Info:");
            System.out.println("ID: " + currentUser.getId());
            System.out.println("Username: " + currentUser.getUsername());
            System.out.println("Role: " + currentUser.getRole());
        }
    }

    private static void navigateToUserRolePage() {
    int choice = 0;
    do {
        if (currentUser.getRole().equals("Admin")) {
            // Admin Menu
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Users");
            System.out.println("2. View All Houses");
            System.out.println("3. Delete User");
            System.out.println("4. Delete House");
            System.out.println("5. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    List<User> users = userService.getAllUsers();
                    for (User user : users) {
                        System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername() + ", Role: " + user.getRole());
                    }
                    break;
                case 2:
                    List<House> houses = houseService.getAvailableHouses();
                    for (House house : houses) {
                        System.out.println("ID: " + house.getId() + ", Address: " + house.getAddress() + ", Price: $" + house.getPrice() + ", Status: " + house.getStatus());
                    }
                    break;
                case 3:
                    System.out.print("Enter user ID to delete: ");
                    int userId = scanner.nextInt();
                    userService.deleteUser(userId);
                    System.out.println("User deleted successfully.");
                    break;
                case 4:
                    System.out.print("Enter house ID to delete: ");
                    int houseId = scanner.nextInt();
                    houseService.deleteHouse(houseId);
                    System.out.println("House deleted successfully.");
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } else if (currentUser.getRole().equals("Owner")) {
            // Owner Menu
            System.out.println("\n--- Owner Menu ---");
            System.out.println("1. Add House");
            System.out.println("2. View Offers");
            System.out.println("3. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addHouse();
                    break;
                case 2:
                    viewOffers();
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } else if (currentUser.getRole().equals("Customer")) {
            // Customer Menu (Add functionality as needed)
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Available Houses");
            System.out.println("2. Make an Offer");
            System.out.println("3. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAvailableHouses();
                    break;
                case 2:
                    makeOffer();
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    } while (choice != 3); // Loop until the user decides to exit to the main menu
}

private static void viewOffers() {
    if (currentUser == null || !currentUser.getRole().equals("Owner")) {
        System.out.println("You must be logged in as an Owner to view offers.");
        return;
    }

    System.out.println("--- Offers for Your Houses ---");
    List<Offer> offers = offerService.getOffersByOwnerId(currentUser.getId());

    if (offers == null || offers.isEmpty()) {
        System.out.println("No offers found for your houses.");
    } else {
        for (Offer offer : offers) {
            System.out.println("Offer ID: " + offer.getId() +
                               ", House ID: " + offer.getHouseId() +
                               ", Customer ID: " + offer.getCustomerId() +
                               ", Offer Price: $" + offer.getOfferPrice() +
                               ", Status: " + offer.getStatus());
        }

        // Prompt for accepting or rejecting an offer
        System.out.print("Enter Offer ID to accept or reject (0 to go back): ");
        int offerId = scanner.nextInt();
        
        if (offerId == 0) {
            return; // Go back to the previous menu
        }

        System.out.print("Enter 1 to Accept or 2 to Reject: ");
        int decision = scanner.nextInt();
        
        if (decision == 1) {
            // Update offer status to Accepted
            offerService.updateOfferStatus(offerId, "Accepted");
            System.out.println("Offer accepted!");
        } else if (decision == 2) {
            // Update offer status to Rejected
            offerService.updateOfferStatus(offerId, "Rejected");
            System.out.println("Offer rejected!");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}

private static void viewAvailableHouses() {
    System.out.println("--- Available Houses ---");
    List<House> houses = houseService.getAvailableHouses();
    if (houses.isEmpty()) {
        System.out.println("No available houses found.");
    } else {
        for (House house : houses) {
            System.out.println("ID: " + house.getId() + ", Address: " + house.getAddress() +
                               ", Price: $" + house.getPrice() + ", Size: " + house.getSize() +
                               " sq ft, Status: " + house.getStatus());
        }
    }
}


    private static void addHouse() {
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter size (in square feet): ");
        int size = scanner.nextInt();
        houseService.addHouse(currentUser.getId(), address, price, size);
        System.out.println("House added successfully!");
    }

    private static void makeOffer() {
        System.out.print("Enter house ID: ");
        int houseId = scanner.nextInt();
        System.out.print("Enter offer price: ");
        double offerPrice = scanner.nextDouble();
        offerService.makeOffer(houseId, currentUser.getId(), offerPrice);
        System.out.println("Offer made successfully!");
    }
}
