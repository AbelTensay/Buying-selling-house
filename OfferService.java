package buyingandselling;

import java.util.List;

public class OfferService {
    private OfferDAO offerDAO = OfferDAO.getInstance(); // Use Singleton

    public void makeOffer(int houseId, int customerId, double offerPrice) {
        Offer offer = new Offer(0, houseId, customerId, offerPrice, "Pending"); // ID will be auto-generated
        offerDAO.addOffer(offer);
    }

    public Offer getOffer(int id) {
        return offerDAO.getOfferById(id);
    }

    public List<Offer> getOffersByOwnerId(int ownerId) {
        return offerDAO.getOffersByOwnerId(ownerId);  // Ensure this method is implemented correctly
    }
}