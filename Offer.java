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
    private String status;    // Pending, Accepted, Rejected
    private int ownerId;      // Add this field

    // Constructor with ownerId
    public Offer(int id, int houseId, int customerId, double offerPrice, String status, int ownerId) {
        this.id = id;
        this.houseId = houseId;
        this.customerId = customerId;
        this.offerPrice = offerPrice;
        this.status = status;
        this.ownerId = ownerId; // Initialize ownerId
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public int getHouseId() {
        return houseId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

    public String getStatus() {
        return status;
    }

    public int getOwnerId() { // Add this getter
        return ownerId;
    }
    
    // Optional Setters
    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
