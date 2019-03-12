import java.util.*;

public class CarPark {
    private String address;
    private String carParkType;
    private String typeOfParkingSystem;
    private Location location;
    private boolean nightParking;
    private boolean freeParking;
    private int totalLots;
    private int lotsTaken;
    private String lotType;
    private int carParkNumber;

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCarParkType() {
        return carParkType;
    }
    public void setCarParkType(String carParkType) {
        this.carParkType = carParkType;
    }
    public String getTypeOfParkingSystem() {
        return typeOfParkingSystem;
    }
    public void setTypeOfParkingSystem(String typeOfParkingSystem) {
        this.typeOfParkingSystem = typeOfParkingSystem;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public boolean getNightParking() {
        return nightParking;
    }
    public void setNightParking(boolean nightParking) {
        this.nightParking = nightParking;
    }
    public boolean getFreeParking() {
        return freeParking;
    }
    public void setFreeParking(boolean freeParking) {
        this.freeParking = freeParking;
    }
    public int getTotalLots() {
        return totalLots;
    }
    public void setTotalLots(int totalLots) {
        this.totalLots = totalLots;
    }
    public int getLotsTaken() {
        return lotsTaken;
    }
    public void setLotsTaken(int lotsTaken) {
        this.lotsTaken = lotsTaken;
    }
    public String getLotType() {
        return lotType;
    }
    public void setLotType(String lotType) {
        this.lotType = lotType;
    }
    public int getCarParkNumber() {
        return carParkNumber;
    }
    public void setCarParkNumber(int carParkNumber) {
        this.carParkNumber = carParkNumber;
    }

}
