package buyingandselling;

import java.util.List;

public class HouseService {
    private HouseDAO houseDAO = HouseDAO.getInstance(); // Use Singleton

    public void addHouse(int ownerId, String address, double price, int size) {
        House house = new House(0, ownerId, address, price, size, "Available"); // ID will be auto-generated
        houseDAO.addHouse(house);
    }

    public House getHouse(int id) {
        return houseDAO.getHouseById(id);
    }

    public List<House> getAvailableHouses() {
        return houseDAO.getAvailableHouses();
    }
}