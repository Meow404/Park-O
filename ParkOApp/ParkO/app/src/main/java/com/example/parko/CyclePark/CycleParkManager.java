package com.example.parko.CyclePark;

import android.util.Log;

import com.example.parko.Extra.Location.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.parko.Extra.Extra.*;

public class CycleParkManager {


    private static String retCycleParkURL(Location currentLocation, double radius) {
        String xCor = String.valueOf(currentLocation.getXCoordinate());
        String yCor = String.valueOf(currentLocation.getYCoordinate());
        String rad = String.valueOf(radius);
        return "http://datamall2.mytransport.sg/ltaodataservice/BicycleParkingv2?Lat=" + xCor + "&Long=" + yCor + "&Dist=" + rad;
    }

    private static CyclePark retCyclePark(JSONObject cycleParkObj) {
        try {
            String description = cycleParkObj.getString("Description");
            String rackType = cycleParkObj.getString("RackType");
            long rackCount = Long.valueOf((Integer) cycleParkObj.get("RackCount"));

            Double xCor = (Double) cycleParkObj.get("Latitude");
            Double yCor = (Double) cycleParkObj.get("Longitude");

            String shelter = cycleParkObj.getString("ShelterIndicator");
            boolean shelterB;
            if (shelter.equals("Y"))
                shelterB = true;
            else
                shelterB = false;

            return new CyclePark(description, xCor, yCor, rackType, rackCount, shelterB);
        } catch (JSONException ex) {
            return null;
        }
    }

    public static ArrayList<CyclePark> returnCycleParksInVicinity(Location location, double constraint) {
        ArrayList<CyclePark> cycleParks = new ArrayList<>();
        String cycleParkApi = retCycleParkURL(location, constraint);
        String dataMallAccountKey = "ALUuk8N1RNOOh8e8lGi3QQ==";
        try {
            JSONObject cycleParkJsonObj = new JSONObject(getHTTPSRequest(cycleParkApi, dataMallAccountKey));
            JSONArray cycleParkJsonArray = cycleParkJsonObj.getJSONArray("value");
            for (int index = 0; index < cycleParkJsonArray.length(); index++) {
                CyclePark cyclePark = retCyclePark(cycleParkJsonArray.getJSONObject(index));
                cycleParks.add(cyclePark);
            }
        } catch (JSONException ex) {
        }
        if (cycleParks.size() != 0)
            order(cycleParks, location);

        return cycleParks;
    }
}
