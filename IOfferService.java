/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buyingandselling;

import java.util.List;

/**
 *
 * @author Abel
 */
public interface IOfferService {
    void makeOffer(int houseId, int customerId, double offerPrice);
    Offer getOffer(int id);
    List<Offer> getOffersByOwnerId(int ownerId);
    void updateOfferStatus(int offerId, String status);
    List<Offer> getOffersByCustomerId(int customerId);
}
