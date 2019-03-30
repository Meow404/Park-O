package CyclePark;

import Extra.Location.Location;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static Extra.Extra.getHTTPSRequest;
import static Extra.Extra.writeUsingOutputStream;

public class CycleParkManager {

    private static String retCycleParkURL(Location currentLocation, double radius) {
        String xCor = String.valueOf(currentLocation.getXCoordinate());
        String yCor = String.valueOf(currentLocation.getYCoordinate());
        String rad = String.valueOf(radius);
        return "http://datamall2.mytransport.sg/ltaodataservice/BicycleParkingv2?Lat=" + xCor + "&Long=" + yCor + "&Dist=" + rad;
    }

    private static CyclePark retCyclePark(JSONObject cycleParkObj) {
        String description = cycleParkObj.getString("Description");
        String rackType = cycleParkObj.getString("RackType");
        long rackCount = Long.valueOf((Integer)cycleParkObj.get("RackCount"));

        Double xCor = (Double) cycleParkObj.get("Latitude");
        Double yCor = (Double) cycleParkObj.get("Longitude");

        String shelter = cycleParkObj.getString("ShelterIndicator");
        boolean shelterB;
        if (shelter.equals("Y"))
            shelterB = true;
        else
            shelterB = false;

        return new CyclePark(description, xCor, yCor, rackType, rackCount, shelterB);
    }

    public static void main(String args[]) {
        ArrayList<CyclePark> cycleParks = new ArrayList<>();
        String cycleParkApi = retCycleParkURL(new Location(1.342678, 103.757214), 0.5);
        String dataMallAccountKey = "ALUuk8N1RNOOh8e8lGi3QQ==";

        JSONObject cycleParkJsonObj = new JSONObject(getHTTPSRequest(cycleParkApi, dataMallAccountKey));
        JSONArray cycleParkJsonArray = cycleParkJsonObj.getJSONArray("value");
        writeUsingOutputStream(cycleParkJsonArray.toString(4), "cycleParks.txt");

        for (int index = 0; index < cycleParkJsonArray.length(); index++) {
            cycleParks.add(retCyclePark(cycleParkJsonArray.getJSONObject(index)));
        }

        for (CyclePark cyclePark : cycleParks) {
            cyclePark.print();
        }
    }
}
