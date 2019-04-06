package Facilities.FacilityTypes;

import Extra.Location.Location;
import Extra.Location.LocationHandler;
import org.json.JSONObject;

import static Extra.Extra.*;

public class Library extends LocationHandler implements FacilityTypes {
    private String facilityType;
    private String name;
    private String address;


    public Library(String name, String address, Double xCor, Double yCor) {
        super(new Location(xCor, yCor));
        facilityType = "Library";
        this.name = name;
        this.address = address;
    }

    public Library(JSONObject jObj) {
        super(jObj);
        facilityType = "Library";

        name = jObj.getString("NAME");
        address = jObj.getString("ADDRESSSTREETNAME");
    }

    public static JSONObject retrieveTheme(Location location, String APIToken, Double constraint) {

        String theme = "libraries";

        Double[] axisConstraints = retAxisConstraints(location, constraint);
        String URL = "https://developers.onemap.sg/privateapi/themesvc/retrieveTheme?queryName=" + theme + "&token=" + APIToken + "&extents=" + axisConstraints[0] +",%20"+axisConstraints[1]+"," + axisConstraints[2] + ",%20"+axisConstraints[3];


        String themeRetrieved = readFromURL(URL);
        JSONObject jObj = new JSONObject(themeRetrieved);
        /* we append the entire JSON data of EACH theme into each index of the ArrayList */
        writeUsingOutputStream(jObj.toString(4), theme + ".txt");

        return jObj;
    }


    public String retType() {
        return this.facilityType;
    }

    public String retName() {
        return this.name;
    }

    public String retAddress() {
        return this.address;
    }

    public Location retLocation() {
        return this.location;
    }

    public void print() {
        System.out.print("\n\n");
        systemSplitOutput("Name",name);
        systemSplitOutput("Address",address);

        /* this could be removable */
//        String locationString = Double.toString(location.getXCoordinate()) + ", " + Double.toString(location.getYCoordinate());
//        System.out.println(String.format("|%15s : %-45s|", "Location", locationString));
    }
}
