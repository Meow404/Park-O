package com.example.parko.Facilities.FacilityTypes;

import com.example.parko.Extra.Location.Location;
import com.example.parko.Extra.Location.LocationHandler;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.parko.Extra.Extra.*;

public class AXSstation extends LocationHandler implements FacilityTypes {
    private String facilityType;
    private String name;
    private String address;
    /* might have to change to xCor, yCor, Lat and Long for all Location location */


    public AXSstation(String name, String address, Double xCor, Double yCor) {
        super(new Location(xCor, yCor));
        facilityType = "AXSstation";
        this.name = name;
        this.address = address;

    }

    public AXSstation(JSONObject jObj) {
        super(jObj);
        facilityType = "AXSstation";

        try {
            name = jObj.getString("AXS_ID");
            address = jObj.getString("DESCRIPTION");
        } catch (JSONException ex) {

        }


    }

    public static JSONObject retrieveTheme(Location location, String APIToken, Double Constraint) {

        String theme = "axs_station";

        Double[] axisConstraints = retAxisConstraints(location, Constraint);
        String URL = "https://developers.onemap.sg/privateapi/themesvc/retrieveTheme?queryName=" + theme + "&token=" + APIToken + "&extents=" + axisConstraints[0] + ",%20" + axisConstraints[1] + "," + axisConstraints[2] + ",%20" + axisConstraints[3];
        String themeRetrieved = readFromURL(URL);
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(themeRetrieved);
            /* we append the entire JSON data of EACH theme into each index of the ArrayList */
        } catch (JSONException ex) {
        }

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
        systemSplitOutput("Name", name);
        systemSplitOutput("Address", address);

        /* this could be removable */
//        String locationString = Double.toString(location.getXCoordinate()) + ", " + Double.toString(location.getYCoordinate());
//        System.out.println(String.format("|%15s : %-45s|", "Location", locationString));
    }
}
