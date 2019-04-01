package Facilities.FacilityTypes;
import Extra.Location.Location;

public class ExerciseFacility implements FacilityTypes {
    private String facilityType;
    private String name;
    private String address;
    /* might have to change to xCor, yCor, Lat and Long for all Location location */
    private Location location;


    public ExerciseFacility(String name, String address, Double xCor, Double yCor){
        facilityType = "ExerciseFacility";
        this.name = name;
        this.address = address;
        this.location = new Location(xCor, yCor);
    }

    public String retType(){ return this.facilityType; }
    public String retName(){ return this.name; }
    public String retAddress(){ return this.address; }
    public Location retLocation(){ return this.location; }

    public void print() {
        System.out.println(String.format("\n|%15s : %-45s|", "Name", name));
        System.out.println(String.format("|%15s : %-45s|", "Address", address));

        /* this could be removable */
        String locationString = Double.toString(location.getXCoordinate()) + ", " + Double.toString(location.getYCoordinate());
        System.out.println(String.format("|%15s : %-45s|", "Location", locationString));
    }
}
