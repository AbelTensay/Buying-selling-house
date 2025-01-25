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
public interface IOfferDAO {
    void addOffer(Offer offer);
    Offer getOfferById(int id);
    List<Offer> getOffersByOwnerId(int ownerId);
    List<Offer> getOffersByCustomerId(int customerId);
    void updateOfferStatus(int offerId, String status);
    int getOwnerIdByHouseId(int houseId);
}
