package Facilities;

import Extra.Location.Location;
import org.json.JSONObject;

public class Facilities {
    private boolean hawkerCentre;
    private boolean axsStation;
    private boolean exerciseFacility;
    private boolean library;
    private boolean supermarket;
    private String name;
    private Location location;

    public Facilities() {
        hawkerCentre = false;
        axsStation = false;
        exerciseFacility = false;
        library = false;
        supermarket = false;
        name = "";
        location = new Location();
    }

    public Facilities(String name, Location location, boolean hawkerCentre, boolean axsStation, boolean gym, boolean library, boolean supermarket) {
        this.hawkerCentre = hawkerCentre;
        this.axsStation = axsStation;
        this.exerciseFacility = gym;
        this.library = library;
        this.supermarket = supermarket;
        this.name = name;
        this.location = location;
    }

    public void print(){
        System.out.println(String.format("|%15s : %-45s|","Hawker Center",hawkerCentre == true ? "True" : "False"));
        System.out.println(String.format("|%15s : %-45s|","axsExpressTopUp",axsStation == true ? "True" : "False"));
        System.out.println(String.format("|%15s : %-45s|","Gym",exerciseFacility == true ? "True" : "False"));
        System.out.println(String.format("|%15s : %-45s|","Library",library == true ? "True" : "False"));
        System.out.println(String.format("|%15s : %-45s|","Name", name));
        System.out.println(String.format("|%15s : %-45s|","Supermarket",supermarket == true ? "True" : "False"));
        System.out.println(String.format("|%15s : %-45s|","X-Coordinate",location.getXCoordinate()));
        System.out.println(String.format("|%15s : %-45s|","Y-Coordinate",location.getYCoordinate()));
    }

    public boolean getHawkerCenter() {
        return hawkerCentre;
    }
    public void setHawkerCentre( boolean newHawkerCentre ) {
        hawkerCentre = newHawkerCentre;
    }
    public boolean getAxsStation(){
        return axsStation;
    }
    public void setAxsStation( boolean newAxsStation ){
        axsStation = newAxsStation;
    }
    public boolean getSupermarket() {
        return supermarket;
    }
    public void setSupermarket( boolean newSupermarket ) {
        hawkerCentre = newSupermarket;
    }
    public boolean getGym(){
        return exerciseFacility;
    }
    public void setGym( boolean newGym ){ exerciseFacility = newGym; }
    public boolean getLibrary(){ return library; }
    public void setLibrary( boolean newLibrary ){ library = newLibrary; }
    public String getName(){
        return name;
    }
    public void setName( String newName ) {
        name = newName;
    }
    public Location getLocation(){
        return location;
    }
    public void setLocation( Location location ){
        this.location = location;
    }

}

