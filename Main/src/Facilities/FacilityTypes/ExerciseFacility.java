package Facilities.FacilityTypes;

import Extra.Location.Location;
import Extra.Location.LocationHandler;
import org.json.JSONException;
import org.json.JSONObject;

import static Extra.Extra.readFromURL;
import static Extra.Extra.splitLatLong;
import static Extra.Extra.writeUsingOutputStream;

public class ExerciseFacility extends LocationHandler implements FacilityTypes {
    private String facilityType;
    private String name;
    private String address;
    /* might have to change to xCor, yCor, Lat and Long for all Location location */
    private Location location;


    public ExerciseFacility(String name, String address, Double xCor, Double yCor) {
        super(new Location(xCor, yCor));
        facilityType = "ExerciseFacility";
        this.name = name;
        this.address = address;

    }

    public ExerciseFacility(JSONObject jObj) {
        super(new Location(splitLatLong(jObj.getString("LatLng"))[0],splitLatLong(jObj.getString("LatLng"))[1]));
        facilityType = "ExerciseFacility";

        name = jObj.getString("NAME");
        try {
            address = jObj.getString("ADDRESSSTREETNAME");
        } catch (JSONException ex) {
            address = "Information Not Available";
        }

        //String geoLoc = jObj.getString("LatLng");
        //Double[] xyCor = splitLatLong(geoLoc);
    }

    public static JSONObject retrieveTheme(Location location, String APIToken) {

        String theme = "exercisefacilities";
        String URL = "https://developers.onemap.sg/privateapi/themesvc/retrieveTheme?queryName=" + theme + "&token=" + APIToken + "&extents=" + location.getXCoordinate() + ",%20103.7796402," + location.getYCoordinate() + ",%20103.8726032";

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
        System.out.println(String.format("\n|%15s : %-45s|", "Name", name));
        System.out.println(String.format("|%15s : %-45s|", "Address", address));

        /* this could be removable */
        String locationString = Double.toString(location.getXCoordinate()) + ", " + Double.toString(location.getYCoordinate());
        System.out.println(String.format("|%15s : %-45s|", "Location", locationString));
    }
}
