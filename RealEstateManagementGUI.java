package buyingandselling;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class RealEstateManagementGUI extends Application {
    private UserService userService = new UserService();
    private HouseService houseService = new HouseService();
    private OfferService offerService = new OfferService();
    private User currentUser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Real Estate Management System");
        showMainMenu(primaryStage);
    }

    private void showMainMenu(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        Label title = new Label("Real Estate Management System");
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Button exitButton = new Button("Exit");

        loginButton.setOnAction(e -> showLoginScreen(primaryStage));
        registerButton.setOnAction(e -> showRegistrationScreen(primaryStage));
        exitButton.setOnAction(e -> primaryStage.close());

        root.getChildren().addAll(title, loginButton, registerButton, exitButton);
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

        private void showLoginScreen(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        Label title = new Label("Login");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginButton = new Button("Login");
        Button backButton = new Button("Back");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            currentUser = userService.authenticateUser(username, password);
            if (currentUser != null) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Login successful!");
                successAlert.showAndWait();
                showRoleMenu(primaryStage);
            } else {
                Alert failureAlert = new Alert(Alert.AlertType.ERROR, "Invalid credentials. Please try again.");
                failureAlert.showAndWait();
            }
        });

        backButton.setOnAction(e -> showMainMenu(primaryStage));

        root.getChildren().addAll(title, usernameField, passwordField, loginButton, backButton);
        primaryStage.setScene(new Scene(root, 300, 200));
    }


    private void showRegistrationScreen(Stage primaryStage) {
    VBox root = new VBox(10);
    root.setPadding(new Insets(10));
    
    Label title = new Label("Register");
    TextField usernameField = new TextField();
    usernameField.setPromptText("Username");
    
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Password");

    ComboBox<String> roleComboBox = new ComboBox<>();
    roleComboBox.getItems().addAll("Customer", "Owner");
    roleComboBox.setPromptText("Select Role");
    
    TextField phoneField = new TextField(); // New phone field
    phoneField.setPromptText("Phone Number");
    
    Button registerButton = new Button("Register");
    Button backButton = new Button("Back");

    registerButton.setOnAction(e -> {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();
        String phoneNumber = phoneField.getText(); // Get phone number

        // Validate input
        if (role != null && !username.isEmpty() && !password.isEmpty() && 
            (role.equals("Customer") && !phoneNumber.isEmpty() || role.equals("Owner"))) {
            userService.registerUser(role, username, password, phoneNumber); // Pass phone number
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, role + " registered successfully!");
            successAlert.showAndWait();
            showMainMenu(primaryStage);
        } else {
            Alert failureAlert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields correctly.");
            failureAlert.showAndWait();
        }
    });

    backButton.setOnAction(e -> showMainMenu(primaryStage));

    root.getChildren().addAll(title, usernameField, passwordField, roleComboBox, phoneField, registerButton, backButton);
    primaryStage.setScene(new Scene(root, 300, 250));
}

    private void showRoleMenu(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        Label title = new Label("Welcome, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
        Button logoutButton = new Button("Logout");

        if (currentUser.getRole().equals("Admin")) {
            // Admin Menu
            Button viewUsersButton = new Button("View All Users");
            Button viewHousesButton = new Button("View All Houses");
            Button deleteUserButton = new Button("Delete User");
            Button deleteHouseButton = new Button("Delete House");

            viewUsersButton.setOnAction(e -> showViewUsersScreen(primaryStage));
            viewHousesButton.setOnAction(e -> showViewHousesScreen(primaryStage));
            deleteUserButton.setOnAction(e -> showDeleteUserScreen(primaryStage));
            deleteHouseButton.setOnAction(e -> showDeleteHouseScreen(primaryStage));

            root.getChildren().addAll(title, viewUsersButton, viewHousesButton, deleteUserButton, deleteHouseButton, logoutButton);
        } else if (currentUser.getRole().equals("Owner")) {
            Button addHouseButton = new Button("Add House");
            Button viewOffersButton = new Button("View Offers");

            addHouseButton.setOnAction(e -> showAddHouseScreen(primaryStage));
            viewOffersButton.setOnAction(e -> showViewOffersScreen(primaryStage));

            root.getChildren().addAll(title, addHouseButton, viewOffersButton, logoutButton);
        } else if (currentUser.getRole().equals("Customer")) {
            Button viewHousesButton = new Button("View Available Houses");
            Button makeOfferButton = new Button("Make Offer");

            viewHousesButton.setOnAction(e -> showViewAvailableHousesScreen(primaryStage));
            makeOfferButton.setOnAction(e -> showMakeOfferScreen(primaryStage));

            root.getChildren().addAll(title, viewHousesButton, makeOfferButton, logoutButton);
        }

        logoutButton.setOnAction(e -> {
            currentUser = null;
            showMainMenu(primaryStage);
        });

        primaryStage.setScene(new Scene(root, 300, 250));
    }
    
    private void showViewUsersScreen(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        Label title = new Label("All Users");
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            root.getChildren().add(new Label("No users found."));
        } else {
            for (User user : users) {
                root.getChildren().add(new Label("ID: " + user.getId() + ", Username: " + user.getUsername() + ", Role: " + user.getRole()));
            }
        }
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showRoleMenu(primaryStage));
        root.getChildren().add(backButton);
        primaryStage.setScene(new Scene(root, 400, 300));
    }
    
    private void showDeleteUserScreen(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        Label title = new Label("Delete User");
        TextField userIdField = new TextField();
        userIdField.setPromptText("User ID");
        Button deleteButton = new Button("Delete User");
        Button backButton = new Button("Back");

        deleteButton.setOnAction(e -> {
            int userId = Integer.parseInt(userIdField.getText());
            userService.deleteUser(userId);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "User with ID " + userId + " has been deleted.");
            alert.showAndWait();
        });

        backButton.setOnAction(e -> showRoleMenu(primaryStage));

        root.getChildren().addAll(title, userIdField, deleteButton, backButton);
        primaryStage.setScene(new Scene(root, 300, 200));
    }

    private void showDeleteHouseScreen(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        Label title = new Label("Delete House");
        TextField houseIdField = new TextField();
        houseIdField.setPromptText("House ID");
        Button deleteButton = new Button("Delete House");
        Button backButton = new Button("Back");

        deleteButton.setOnAction(e -> {
            int houseId = Integer.parseInt(houseIdField.getText());
            houseService.deleteHouse(houseId);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "House with ID " + houseId + " has been deleted.");
            alert.showAndWait();
        });

        backButton.setOnAction(e -> showRoleMenu(primaryStage));

        root.getChildren().addAll(title, houseIdField, deleteButton, backButton);
        primaryStage.setScene(new Scene(root, 300, 200));
    }

    
    private void showViewHousesScreen(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        Label title = new Label("All Houses");
        List<House> houses = houseService.getAvailableHouses();

        if (houses.isEmpty()) {
            root.getChildren().add(new Label("No houses found."));
        } else {
            for (House house : houses) {
                root.getChildren().add(new Label("ID: " + house.getId() + ", Address: " + house.getAddress() + ", Price: $" + house.getPrice() + ", Status: " + house.getStatus()));
            }
        }
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showRoleMenu(primaryStage));
        root.getChildren().add(backButton);
        primaryStage.setScene(new Scene(root, 400, 300));
    }
    
    private void showViewMyOffersScreen(Stage primaryStage) {
    VBox root = new VBox(10);
    root.setPadding(new Insets(10));
    Label title = new Label("My Offers");
    List<Offer> offers = offerService.getOffersByOwnerId(currentUser.getId()); // Get offers made to the owner

    if (offers.isEmpty()) {
        root.getChildren().add(new Label("No offers found."));
    } else {
        for (Offer offer : offers) {
            HBox offerBox = new HBox(10);
            offerBox.setPadding(new Insets(5));
            offerBox.getChildren().add(new Label("Offer ID: " + offer.getId() +
                    ", House ID: " + offer.getHouseId() +
                    ", Price: $" + offer.getOfferPrice() +
                    ", Status: " + offer.getStatus()));

            // Only show the "Show Customer Phone" button for accepted offers
            if (offer.getStatus().equals("Accepted")) {
                Button showPhoneButton = new Button("Show Customer Phone");
                showPhoneButton.setOnAction(e -> {
                    String customerPhone = userService.getCustomerPhoneById(offer.getCustomerId()); // Get the customer's phone by offer
                    if (customerPhone != null) {
                        Alert phoneAlert = new Alert(Alert.AlertType.INFORMATION, "Customer's phone number is: " + customerPhone);
                        phoneAlert.showAndWait();
                    } else {
                        Alert phoneAlert = new Alert(Alert.AlertType.ERROR, "Customer's phone number not found.");
                        phoneAlert.showAndWait();
                    }
                });
                offerBox.getChildren().add(showPhoneButton);
            }

            if (offer.getStatus().equals("Pending")) {
                Button noChangeButton = new Button("No Change");
                noChangeButton.setOnAction(e -> {
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION, "Your offer is still pending.");
                    infoAlert.showAndWait();
                });
                offerBox.getChildren().add(noChangeButton);
            } else if (offer.getStatus().equals("Accepted")) {
                Button buyButton = new Button("Buy");
                buyButton.setOnAction(e -> {
                    // Logic for buying can be added here if necessary
                    Alert buyAlert = new Alert(Alert.AlertType.INFORMATION, "Proceed with buying House ID: " + offer.getHouseId());
                    buyAlert.showAndWait();
                });
                offerBox.getChildren().add(buyButton);
            }

            root.getChildren().add(offerBox);
        }
    }

    Button backButton = new Button("Back");
    backButton.setOnAction(e -> showRoleMenu(primaryStage));

    root.getChildren().add(backButton);
    primaryStage.setScene(new Scene(root, 400, 300));
}

    private void showAddHouseScreen(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        Label title = new Label("Add House");
        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        TextField priceField = new TextField();
        priceField.setPromptText("Price");
        TextField sizeField = new TextField();
        sizeField.setPromptText("Size (sq ft)");
        Button addHouseButton = new Button("Add House");
        Button backButton = new Button("Back");

        addHouseButton.setOnAction(e -> {
            String address = addressField.getText();
            double price = Double.parseDouble(priceField.getText());
            int size = Integer.parseInt(sizeField.getText());
            houseService.addHouse(currentUser.getId(), address, price, size);
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "House added successfully!");
            successAlert.showAndWait();
            showRoleMenu(primaryStage);
        });

        backButton.setOnAction(e -> showRoleMenu(primaryStage));

        root.getChildren().addAll(title, addressField, priceField, sizeField, addHouseButton, backButton);
        primaryStage.setScene(new Scene(root, 300, 250));
    }

    private void showViewOffersScreen(Stage primaryStage) {
    VBox root = new VBox(10);
    root.setPadding(new Insets(10));
    Label title = new Label("Offers for Your Houses");
    List<Offer> offers = offerService.getOffersByOwnerId(currentUser.getId());

    if (offers.isEmpty()) {
        root.getChildren().add(new Label("No offers found."));
    } else {
        for (Offer offer : offers) {
            HBox offerBox = new HBox(10);
            offerBox.setPadding(new Insets(5));
            offerBox.getChildren().add(new Label("Offer ID: " + offer.getId() + ", House ID: " + offer.getHouseId() +
                    ", Customer ID: " + offer.getCustomerId() + ", Price: $" + offer.getOfferPrice() +
                    ", Status: " + offer.getStatus()));

            // Accept button
            Button acceptButton = new Button("Accept");
            acceptButton.setOnAction(e -> {
                offerService.updateOfferStatus(offer.getId(), "Accepted");
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Offer accepted!");
                successAlert.showAndWait();
                showViewOffersScreen(primaryStage); // Refresh the offers
            });

            // Reject button
            Button rejectButton = new Button("Reject");
            rejectButton.setOnAction(e -> {
                offerService.updateOfferStatus(offer.getId(), "Rejected");
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Offer rejected!");
                successAlert.showAndWait();
                showViewOffersScreen(primaryStage); // Refresh the offers
            });

            offerBox.getChildren().addAll(acceptButton, rejectButton);
            root.getChildren().add(offerBox);
        }
    }
    Button backButton = new Button("Back");
    backButton.setOnAction(e -> showRoleMenu(primaryStage));

    root.getChildren().add(backButton);
    primaryStage.setScene(new Scene(root, 600, 400));
}

    private void showViewAvailableHousesScreen(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        Label title = new Label("Available Houses");
        List<House> houses = houseService.getAvailableHouses();
        if (houses.isEmpty()) {
            root.getChildren().add(new Label("No available houses found."));
        } else {
            for (House house : houses) {
                root.getChildren().add(new Label("House ID: " + house.getId() + ", Address: " + house.getAddress() +
                        ", Price: $" + house.getPrice() + ", Size: " + house.getSize() +
                        " sq ft, Status: " + house.getStatus()));
            }
        }
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showRoleMenu(primaryStage));

        root.getChildren().add(backButton);
        primaryStage.setScene(new Scene(root, 400, 300));
    }

    private void showMakeOfferScreen(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        Label title = new Label("Make an Offer");
        TextField houseIdField = new TextField();
        houseIdField.setPromptText("House ID");
        TextField offerPriceField = new TextField();
        offerPriceField.setPromptText("Offer Price");
        Button makeOfferButton = new Button("Make Offer");
        Button backButton = new Button("Back");

        makeOfferButton.setOnAction(e -> {
            int houseId = Integer.parseInt(houseIdField.getText());
            double offerPrice = Double.parseDouble(offerPriceField.getText());
            offerService.makeOffer(houseId, currentUser.getId(), offerPrice);
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Offer made successfully!");
            successAlert.showAndWait();
            showRoleMenu(primaryStage);
        });

        backButton.setOnAction(e -> showRoleMenu(primaryStage));

        root.getChildren().addAll(title, houseIdField, offerPriceField, makeOfferButton, backButton);
        primaryStage.setScene(new Scene(root, 300, 250));
    }
}