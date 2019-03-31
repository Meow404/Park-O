package Facilities;
import Extra.Location.Location;

public class ExerciseFacility {
    private String name;
    private String address;
    private String postalCode;
    private Location location;

    public ExeciseFacility(){
        name = null;
        address = null;
        postalCode = null;
        location = null;
    }

    public ExerciseFacility(String name, String address, String postalCode){
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.location = location;
    }

    public String getAddress(){ return this.address; }
    public String getName(){ return this.name; }
    public String getPostal(){ return this.postalCode; }
    public void setName(String name){ this.name = name; }
    public void setAddress(String address){ this.address = address; }
    public void setPostal(String postalCode){ this.postalCode = postalCode; }

    public void print() {
        System.out.println(String.format("\n|%15s : %-45s|", "Name", name));
        System.out.println(String.format("|%15s : %-45s|", "Address", address));
        System.out.println(String.format("|%15s : %-45d|", "Postal Code", postalCode));
        /* this could be removable */
        String locationString = Double.toString(location.getXCoordinate()) + ", " + Double.toString(location.getYCoordinate());
        System.out.println(String.format("|%15s : %-45s|", "Location", locationString));
    }
}
