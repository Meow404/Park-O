package CarPark;
import Extra.Location.Location;

import java.util.Comparator;

public class CarPark {
    private String address;
    private String carParkType;
    private String typeOfParkingSystem;
    private Location location;
    private boolean nightParking;
    private boolean freeParking;
    private int totalLots;
    private int lotsAvailable;
    private String lotType;
    private String carParkNumber;


    public CarPark(){
        address = "";
        carParkType = "";
        typeOfParkingSystem = "";
        location = new Location();
        nightParking = false;
        freeParking = false;
        totalLots = 0;
        lotsAvailable = 0;
        lotType = "";
        carParkNumber = "";
    }

    public CarPark(String carParkNumber, String address, Location location, String carParkType, String typeOfParkingSystem, boolean freeParking, boolean nightParking){
        this.address = address;
        this.carParkType = carParkType;
        this.typeOfParkingSystem = typeOfParkingSystem;
        this.location = location;
        this.nightParking = nightParking;
        this.freeParking = freeParking;
        totalLots = 0;
        lotsAvailable = 0;
        lotType = "";
        this.carParkNumber = carParkNumber;
    }

    public void update(CarParkUpdate carParkUpdate){
        totalLots = carParkUpdate.getTotalLots();
        lotsAvailable = carParkUpdate.getLotsAvailable();
        lotType = carParkUpdate.getLotType();
    }

    public void print(){
        System.out.println(String.format("|%15s : %-45s|","Number",carParkNumber));
        System.out.println(String.format("|%15s : %-45s|","Address",address));
        System.out.println(String.format("|%15s : %-45s|","Type",carParkType));
        System.out.println(String.format("|%15s : %-45s|","Total Lots",totalLots));
        System.out.println(String.format("|%15s : %-45s|","Lots Available",lotsAvailable));
        System.out.println(String.format("|%15s : %-45s|","Lot Type",lotType));
        System.out.println(String.format("|%15s : %-45s|","System",typeOfParkingSystem));
        System.out.println(String.format("|%15s : %-45.6f|","X-Coordinate",location.getXCoordinate()));
        System.out.println(String.format("|%15s : %-45.6f|","Y-Coordinate",location.getYCoordinate()));
        System.out.println(String.format("|%15s : %-45s|","Free Parking",freeParking));
        System.out.println(String.format("|%15s : %-45s|\n\n","Night Parking",nightParking));

    }
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
        return lotsAvailable;
    }
    public void setLotsTaken(int lotsTaken) {
        this.lotsAvailable = lotsTaken;
    }
    public String getLotType() {
        return lotType;
    }
    public void setLotType(String lotType) {
        this.lotType = lotType;
    }
    public String getCarParkNumber() {
        return carParkNumber;
    }
    public void setCarParkNumber(String carParkNumber) {
        this.carParkNumber = carParkNumber;
    }

}
