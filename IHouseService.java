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
public interface IHouseService {
    void addHouse(int ownerId, String address, double price, int size);
    House getHouse(int id);
    List<House> getAvailableHouses();
    void deleteHouse(int houseId);
}