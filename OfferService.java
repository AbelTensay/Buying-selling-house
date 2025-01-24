package buyingandselling;

import java.util.List;

public class OfferService {
    private OfferDAO offerDAO = OfferDAO.getInstance(); // Use Singleton

    public void makeOffer(int houseId, int customerId, double offerPrice) {
    // Retrieve ownerId from the Houses table using houseId
    int ownerId = getOwnerIdByHouseId(houseId);
    
    // Check if ownerId is valid
    if (ownerId == -1) {
        System.out.println("Invalid house ID. Offer cannot be made.");
        return; // Exit the method if the house ID is invalid
    }
    
    // Create the offer with the ownerId
    Offer offer = new Offer(0, houseId, customerId, offerPrice, "Pending", ownerId); // ID will be auto-generated
    offerDAO.addOffer(offer);
}

private int getOwnerIdByHouseId(int houseId) {
    return offerDAO.getOwnerIdByHouseId(houseId); // Assuming you have this method in OfferDAO
}

    public Offer getOffer(int id) {
        return offerDAO.getOfferById(id);
    }

    public List<Offer> getOffersByOwnerId(int ownerId) {
        return offerDAO.getOffersByOwnerId(ownerId);  // Ensure this method is implemented correctly
    }
    
    public void updateOfferStatus(int offerId, String status) {
        Offer offer = offerDAO.getOfferById(offerId);
        if (offer != null) {
            // Update the offer status in DAO
            offerDAO.updateOfferStatus(offerId, status);
        } else {
            System.out.println("Offer not found.");
        }
    }
    
    public List<Offer> getOffersByCustomerId(int customerId) {
        return offerDAO.getOffersByCustomerId(customerId);
    }
}
