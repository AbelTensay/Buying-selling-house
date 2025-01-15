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
    public Customer(int id, String username, String password) {
        super(id, username, password, "Customer");
    }
}