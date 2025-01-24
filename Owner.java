/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buyingandselling;

/**
 *
 * @author Abel
 */

public class Owner extends User {
    private String phoneNumber; // Field to store the owner's phone number

    public Owner(int id, String username, String password) {
        super(id, username, password, "Owner");
    }

    // Constructor with phone number
    public Owner(int id, String username, String password, String phoneNumber) {
        super(id, username, password, "Owner");
        this.phoneNumber = phoneNumber; // Initialize phone number
    }

    // Getter for phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setter for phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
