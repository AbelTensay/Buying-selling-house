/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buyingandselling;

public class UserFactory {
    public static User createUser(String type, int id, String username, String password, String phoneNumber) {
        switch (type) {
            case "Customer":
                return new Customer(id, username, password, phoneNumber); // Pass phone number
            case "Owner":
                return new Owner(id, username, password); // Assuming Owner does not need a phone number here
            case "Admin":
                return new Admin(id, username, password); // Assuming Admin does not need a phone number
            default:
                throw new IllegalArgumentException("Unknown user type");
        }
    }
}
