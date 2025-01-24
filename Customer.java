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

public class Customer extends User {
    private String phoneNumber; // New field for phone number

    // Constructor that includes the phone number
    public Customer(int id, String username, String password, String phoneNumber) {
        super(id, username, password, "Customer");
        this.phoneNumber = phoneNumber; // Initialize the phone number
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
