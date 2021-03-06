package com.example.parko.Facilities.FacilityTypes;

import com.example.parko.Extra.Location.Location;
import com.example.parko.Extra.Location.LocationHandler;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.parko.Extra.Extra.*;

public class ExerciseFacility extends LocationHandler implements FacilityTypes {
    private String facilityType;
    private String name;
    private String address;


    public ExerciseFacility(String name, String address, Double xCor, Double yCor) {
        super(new Location(xCor, yCor));
        facilityType = "ExerciseFacility";
        this.name = name;
        this.address = address;

    }

    public ExerciseFacility(JSONObject jObj) {
        super(jObj);
        facilityType = "ExerciseFacility";
        try {
            name = jObj.getString("NAME");
            address = jObj.getString("ADDRESSSTREETNAME");
        } catch (JSONException ex) {
            address = "Information Not Available";
        }
    }

    public static JSONObject retrieveTheme(Location location, String APIToken, Double constraint) {

        String theme = "exercisefacilities";

        Double[] axisConstraints = retAxisConstraints(location, constraint);
        String URL = "https://developers.onemap.sg/privateapi/themesvc/retrieveTheme?queryName=" + theme + "&token=" + APIToken + "&extents=" + axisConstraints[0] + ",%20" + axisConstraints[1] + "," + axisConstraints[2] + ",%20" + axisConstraints[3];


        String themeRetrieved = readFromURL(URL);
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(themeRetrieved);

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
