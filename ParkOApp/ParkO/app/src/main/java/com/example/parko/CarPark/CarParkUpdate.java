package com.example.parko.CarPark;

import com.example.parko.Extra.Extra;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.parko.Extra.Extra.*;

public class CarParkUpdate {
    private int lotsAvailable;
    private int totalLots;
    private String lotType;

    private static CarParkUpdate updateCarPark(JSONObject carPark) {
        CarParkUpdate carParkUpdate = new CarParkUpdate();
        try {
            carParkUpdate.setLastUpdate(carPark.getString("update_datetime"));
            JSONArray carParkInfo = carPark.getJSONArray("carpark_info");

            carParkUpdate.setLotsAvailable(Integer.valueOf(carParkInfo.getJSONObject(0).getString("lots_available")));
            carParkUpdate.setTotalLots(Integer.valueOf(carParkInfo.getJSONObject(0).getString("total_lots")));
            carParkUpdate.setLotType(carParkInfo.getJSONObject(0).getString("lot_type"));
        } catch (JSONException ex) {
        }
        return carParkUpdate;
    }

    private static ArrayList<CarPark> updateAllCarPark(JSONObject obj, ArrayList<CarPark> carParks) {

        //This is due to the format of data obtained from API
        try {
          //  Extra.writeUsingOutputStream(obj.toString(4), "CarPark.txt");

            ArrayList<CarPark> updatedCarPark = new ArrayList<>();
            JSONArray items = obj.getJSONArray("items");
            JSONObject firstObject = items.getJSONObject(0);
            JSONArray carParkDataArray = firstObject.getJSONArray("carpark_data");

          //  Extra.writeUsingOutputStream(items.toString(4), "CarPark.txt");

            for (int dataIndex = 0; dataIndex < carParkDataArray.length(); dataIndex++) {
                JSONObject carParkData = carParkDataArray.getJSONObject(dataIndex);
                for (CarPark carPark : carParks)
                    if (carPark.getCarParkNumber().equals(carParkData.getString("carpark_number"))) {
                        CarParkUpdate carParkUpdate = updateCarPark(carParkData);
                        carPark.update(carParkUpdate);
                        updatedCarPark.add(carPark);
                    }

            }

            return updatedCarPark;
        } catch (JSONException ex) {
            return null;
        }
    }

    public static ArrayList<CarPark> getCarParkAvailability(ArrayList<CarPark> carParks) {
        String inputLine = "{}";
        inputLine = readFromURL("https://api.data.gov.sg/v1/transport/carpark-availability");
        JSONObject obj = null;
        try {
            obj = new JSONObject(inputLine);
        } catch (JSONException ex) {
        }
        return updateAllCarPark(obj, carParks);

    }

    public int getLotsAvailable() {
        return lotsAvailable;
    }

    public void setLotsAvailable(int lotsAvailable) {
        this.lotsAvailable = lotsAvailable;
    }

    public int getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(int totalLots) {
        this.totalLots = totalLots;
    }

    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    private String lastUpdate;

    public CarParkUpdate() {
        lotsAvailable = 0;
        totalLots = 0;
        lotType = "";
        lastUpdate = "";
    }

    public CarParkUpdate(int lotsAvailable, int totalLots, String lotType, String lastUpdate) {
        this.lotsAvailable = lotsAvailable;
        this.totalLots = totalLots;
        this.lotType = lotType;
        this.lastUpdate = lastUpdate;
    }
}
