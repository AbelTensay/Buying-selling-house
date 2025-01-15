/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buyingandselling;

public class UserFactory {
    public static User createUser(String type, int id, String username, String password) {
        switch (type) {
            case "Customer":
                return new Customer(id, username, password);
            case "Owner":
                return new Owner(id, username, password);
            case "Admin":
                return new Admin(id, username, password);
            default:
                throw new IllegalArgumentException("Unknown user type");
        }
    }
}