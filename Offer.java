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


public class Offer {
    private int id;
    private int houseId;
    private int customerId;
    private double offerPrice;
    private String status; // Pending, Accepted, Rejected

    public Offer(int id, int houseId, int customerId, double offerPrice, String status) {
        this.id = id;
        this.houseId = houseId;
        this.customerId = customerId;
        this.offerPrice = offerPrice;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public int getHouseId() { return houseId; }
    public int getCustomerId() { return customerId; }
    public double getOfferPrice() { return offerPrice; }
    public String getStatus() { return status; }
}
