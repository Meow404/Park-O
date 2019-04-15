package com.example.parko.CarPark;

import com.example.parko.Extra.Location.Location;
import com.example.parko.Extra.Location.LocationHandler;

/**
 * This is an entity class which holds the necessary information
 * of a single car park lot
 *
 * @author Thomas Stephen Felix
 * @version 1.0
 */

public class CarPark extends LocationHandler {

    private String address;
    private String carParkType;
    private String typeOfParkingSystem;
    private boolean nightParking;
    private boolean freeParking;
    private int totalLots;
    private int lotsAvailable;
    private String lotType;
    private String carParkNumber;

    /**
     * <p> This is the default constructor of the class
     * </p>
     */
    public CarPark() {
        super(new Location());
        address = "";
        carParkType = "";
        typeOfParkingSystem = "";
        nightParking = false;
        freeParking = false;
        totalLots = 0;
        lotsAvailable = 0;
        lotType = "";
        carParkNumber = "";
    }

    /**
     * <p> This is a parameterised constructor of the class </p>
     * @param carParkNumber the unique car park number given to each
     *                      car park in Singapore.
     * @param address address of the car park
     * @param location variable to hold the geo coordinates of the car
     *                 park
     * @param carParkType the type of the car park such as MULTI-STOREY,
     *                    Basement, etc.
     * @param typeOfParkingSystem Electronic, Coupon, etc.
     * @param freeParking is free parking available during weekends
     * @param nightParking is night parking available
     */
    public CarPark(String carParkNumber, String address, Location location, String carParkType, String typeOfParkingSystem, boolean freeParking, boolean nightParking) {
        super(location);
        this.address = address;
        this.carParkType = carParkType;
        this.typeOfParkingSystem = typeOfParkingSystem;
        this.nightParking = nightParking;
        this.freeParking = freeParking;
        totalLots = 0;
        lotsAvailable = 0;
        lotType = "";
        this.carParkNumber = carParkNumber;
    }

    /**
     * <p> update the dynamic values of car park i.e number of parking
     * lots available </p>
     * @param carParkUpdate special objects which holds the value of the
     *                      number of parking lots available
     */

    public void update(CarParkUpdate carParkUpdate) {
        totalLots = carParkUpdate.getTotalLots();
        lotsAvailable = carParkUpdate.getLotsAvailable();
        lotType = carParkUpdate.getLotType();
    }

    /**
     * <p> Print all information about the car parks in a standard
     * format. Used for debugging reasons </p>
     */
    public void print() {
        System.out.println(String.format("|%15s : %-45s|", "Number", carParkNumber));
        System.out.println(String.format("|%15s : %-45s|", "Address", address));
        System.out.println(String.format("|%15s : %-45s|", "Type", carParkType));
        System.out.println(String.format("|%15s : %-45s|", "Total Lots", totalLots));
        System.out.println(String.format("|%15s : %-45s|", "Lots Available", lotsAvailable));
        System.out.println(String.format("|%15s : %-45s|", "Lot Type", lotType));
        System.out.println(String.format("|%15s : %-45s|", "System", typeOfParkingSystem));
        System.out.println(String.format("|%15s : %-45.6f|", "X-Coordinate", location.getXCoordinate()));
        System.out.println(String.format("|%15s : %-45.6f|", "Y-Coordinate", location.getYCoordinate()));
        System.out.println(String.format("|%15s : %-45s|", "Free Parking", freeParking));
        System.out.println(String.format("|%15s : %-45s|\n\n", "Night Parking", nightParking));

    }

    /**
     * <p> to get address of car park</p>
     * @return address of car park
     */
    public String getAddress() {
        return address;
    }

    /**
     * <p> to set the address of car park </p>
     * @param address the address to be set to
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * <p> to get the car park type of the car park</p>
     * @return type of car park
     */

    public String getCarParkType() {
        return carParkType;
    }

    /**
     * <p> to set the type of the car park</p>
     * @param carParkType type of the car park to be set to
     */
    public void setCarParkType(String carParkType) {
        this.carParkType = carParkType;
    }

    /**
     * <p> to get the type of parking system the car park uses</p>
     * @return the type of parking system
     */
    public String getTypeOfParkingSystem() {
        return typeOfParkingSystem;
    }

    /**
     * <p> to set the type of parking syster the car park uses</p>
     * @param typeOfParkingSystem the type of parking system, the
     *                            car park must be set to
     */
    public void setTypeOfParkingSystem(String typeOfParkingSystem) {
        this.typeOfParkingSystem = typeOfParkingSystem;
    }

    /**
     * <p> if night parking is available for car park</p>
     * @return true if night parking is available else false
     */
    public boolean getNightParking() {
        return nightParking;
    }

    /**
     * <p> to set whether night parking is available </p>
     * @param nightParking true if night parking is available else
     *                     false
     */
    public void setNightParking(boolean nightParking) {
        this.nightParking = nightParking;
    }

    /**
     * <p> to know whether free parking is available during the
     * weekends </p>
     * @return true if free parking is available else false
     */
    public boolean getFreeParking() {
        return freeParking;
    }

    /**
     * <p> to set whether free parking is available during weekends</p>
     * @param freeParking is set to true if free parking is
     *                    available else false
     */
    public void setFreeParking(boolean freeParking) {
        this.freeParking = freeParking;
    }

    /**
     * <p> to get the total number of lots at the parking lot</p>
     * @return the number of parking lots at the car park
     */
    public int getTotalLots() {
        return totalLots;
    }

    /**
     * <p> to set the number of parking lots at the car park</p>
     * @param totalLots the vnumber of total lots at car park
     */
    public void setTotalLots(int totalLots) {
        this.totalLots = totalLots;
    }

    /**
     * <p> returns the total lots taken at the car park</p>
     * @return lots occupied at car park
     */
    public int getLotsTaken() {
        return lotsAvailable;
    }

    /**
     *
     * @param lotsTaken
     */
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
