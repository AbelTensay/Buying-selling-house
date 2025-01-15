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


public class House {
    private int id;
    private int ownerId;
    private String address;
    private double price;
    private int size; // in square feet
    private String status; // Available, Sold

    public House(int id, int ownerId, String address, double price, int size, String status) {
        this.id = id;
        this.ownerId = ownerId;
        this.address = address;
        this.price = price;
        this.size = size;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public int getOwnerId() { return ownerId; }
    public String getAddress() { return address; }
    public double getPrice() { return price; }
    public int getSize() { return size; }
    public String getStatus() { return status; }
}